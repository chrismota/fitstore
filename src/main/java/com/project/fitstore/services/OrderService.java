package com.project.fitstore.services;

import com.project.fitstore.domain.OrderItem.OrderItem;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.order.*;
import com.project.fitstore.repositories.OrderItemRepository;
import com.project.fitstore.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public OrderListResponseDto getAllOrders() {
        return OrderListResponseDto.from(orderRepository.findAll());
    }

    public OrderResponseDto getOrder(UUID id) {
        return OrderResponseDto.from(findOrderById(id));
    }

    @Transactional
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto, UUID customerId) {
        checkIfCustomerExists(customerId);
        checkIfProductExists(createOrderDto);

        Order order = orderRepository.save(createOrderDto.toOrder(customerId, getExpirationDate()));

        var orderItemList = createItemsList(createOrderDto, order);
        order.setItems(orderItemList);
        order.setTotal(getTotalPrice(orderItemList));

        return OrderResponseDto.from(orderRepository.save(order));
    }

    public List<OrderItem> createItemsList(CreateOrderDto createOrderDto, Order order) {
        List<OrderItem> itemList = new ArrayList<>();

        for (var item : createOrderDto.products()) {
            var orderItem = createItem(item, order);
            itemList.add(orderItemRepository.save(orderItem));
        }
        return itemList;
    }

    private OrderItem createItem(CreateItemDto itemDto, Order order){
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

    public OrderResponseDto updateOrderStatus(UpdateOrderStatusDto orderStatusDto, UUID id) {
        Order order = findOrderById(id);
        order.setStatus(orderStatusDto.status());
        order.setUpdatedAt(LocalDateTime.now());
        return OrderResponseDto.from(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        orderRepository.delete(this.findOrderById(id));
    }

    private void checkIfCustomerExists(UUID customerId) {
        customerService.findCustomerById(customerId);
    }

    private void checkIfProductExists(CreateOrderDto createOrderDto) {
        for (var product : createOrderDto.products()) {
            productService.findProductById(product.id());
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
        throw new RuntimeException("Order not found");
    }

    private LocalDateTime getExpirationDate(){
        return LocalDateTime.now().plusHours(ORDER_EXPIRATION_HOURS);
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

}
