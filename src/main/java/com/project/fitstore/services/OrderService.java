package com.project.fitstore.services;

import com.project.fitstore.domain.OrderItem.OrderItem;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.order.*;
import com.project.fitstore.exceptions.general.InvalidStatusException;
import com.project.fitstore.exceptions.order.OrderExpiredException;
import com.project.fitstore.exceptions.order.OrderHasPaymentRecordException;
import com.project.fitstore.exceptions.order.OrderNotFoundException;
import com.project.fitstore.exceptions.order.OrderNotValidException;
import com.project.fitstore.repositories.OrderItemRepository;
import com.project.fitstore.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final OrderItemRepository orderItemRepository;
    final CustomerService customerService;
    final ProductService productService;

    private static final int ORDER_EXPIRATION_HOURS = 12;

    public GetAllOrdersResponse getAllOrders() {
        return GetAllOrdersResponse.from(orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    public GetAllOrdersResponse getAllOrdersFromCustomer(UUID customerId) {
        customerService.checkIfCustomerExists(customerId);
        return GetAllOrdersResponse.from(orderRepository.findOrdersByCustomerIdOrderByCreatedAtDesc(customerId));
    }

    public GetAllOrdersResponse getOrdersFromCustomerByStatus(String statusParam, UUID customerId) {
        Status status;
        try {
            status = Status.valueOf(statusParam.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException("Invalid parameter value for status: " + statusParam);
        }

        customerService.checkIfCustomerExists(customerId);

        return GetAllOrdersResponse.from(orderRepository
                .findOrdersByStatusAndCustomerIdOrderByCreatedAtDesc(status, customerId));
    }

    public GetOrderResponse getOrderFromCustomer(UUID orderId, UUID customerId) {
        var order = findOrderByIdAndCustomerId(orderId, customerId);
        return GetOrderResponse.from(order);
    }

    public GetOrderResponse getOrder(UUID orderId) {
        return GetOrderResponse.from(findOrderById(orderId));
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, UUID customerId) {
        customerService.checkIfCustomerExists(customerId);
        productService.checkIfProductsExists(createOrderRequest);

        Order order = createOrderRequest.toOrder(customerId, getExpirationDate());

        List<OrderItem> orderItemList = createItemsList(createOrderRequest, order);
        BigDecimal totalPrice = getTotalPrice(orderItemList);

        order.setItems(orderItemList);
        order.setTotalValue(totalPrice);
        order.setTotalWithDiscount(totalPrice);

        order = orderRepository.save(order);
        orderItemRepository.saveAll(orderItemList);

        return CreateOrderResponse.from(order);
    }

    public List<OrderItem> createItemsList(CreateOrderRequest createOrderRequest, Order order) {
        List<OrderItem> orderItemList = new ArrayList<>();

        for (var item : createOrderRequest.products()) {
            OrderItem orderItem = createItem(item, order);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private OrderItem createItem(CreateItemRequest itemDto, Order order) {
        Product productObj = productService.findProductById(itemDto.id());
        BigDecimal totalPriceProduct = productObj.getPrice().multiply(new BigDecimal(itemDto.quantity()));

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productObj);
        orderItem.setUnityPrice(productObj.getPrice());
        orderItem.setQuantity(itemDto.quantity());
        orderItem.setTotal(totalPriceProduct);
        orderItem.setOrder(order);
        orderItem.setCreatedAt(LocalDateTime.now());
        orderItem.setUpdatedAt(LocalDateTime.now());

        return orderItem;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void orderScheduler() {
        List<Order> orders = orderRepository.findOrdersByStatusAndExpiresAtBefore(Status.PENDING, LocalDateTime.now());
        for (Order order : orders) {
            order.setStatus(Status.FAILED);
            order.setUpdatedAt(LocalDateTime.now());
        }
        orderRepository.saveAll(orders);
    }

    public void deleteOrder(UUID id) {
        try {
            orderRepository.delete(this.findOrderById(id));
        } catch (DataIntegrityViolationException e) {
            throw new OrderHasPaymentRecordException();
        }
    }

    public void checkIfOrderIsValid(Order order) {
        if (order.getStatus() != Status.PENDING) {
            throw new OrderNotValidException();
        }
    }

    public void checkIfOrderIsExpired(Order order) {
        if (order.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new OrderExpiredException();
        }
    }

    private BigDecimal getTotalPrice(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItem::getTotal)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public Order findOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException();
    }

    public Order findOrderByIdAndCustomerId(UUID id, UUID customerId) {
        Optional<Order> order = orderRepository.findOrderByIdAndCustomerId(id, customerId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException();
    }

    private LocalDateTime getExpirationDate() {
        return LocalDateTime.now().plusHours(ORDER_EXPIRATION_HOURS);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

}
