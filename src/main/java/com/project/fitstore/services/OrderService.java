package com.project.fitstore.services;

import com.project.fitstore.domain.OrderItem.OrderItem;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.order.CreateOrderDto;
import com.project.fitstore.dtos.order.OrderResponseDto;
import com.project.fitstore.repositories.OrderItemRepository;
import com.project.fitstore.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final OrderItemRepository orderItemRepository;
    final CustomerService customerService;
    final ProductService productService;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto, UUID customerId) {
        checkIfCustomerExists(customerId);
        checkIfProductExists(createOrderDto);

        Order order = orderRepository.save(createOrderDto.toOrder(customerId));

        var orderItemList = createOrderItem(createOrderDto, order);
        order.setItems(orderItemList);
        order.setTotal(getTotalPrice(orderItemList));

        return createOrderResponse(orderRepository.save(order));
    }

    private OrderResponseDto createOrderResponse(Order order) {
        return new OrderResponseDto(order.getId());
    }

    public List<OrderItem> createOrderItem(CreateOrderDto createOrderDto, Order order) {

        List<OrderItem> orderItemList = new ArrayList<>();

        for (var item : createOrderDto.products()) {
            Product productObj = productService.findProductById(item.id());

            BigDecimal totalPriceProduct = productObj.getPrice().multiply(new BigDecimal(item.quantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productObj);
            orderItem.setUnityPrice(productObj.getPrice());
            orderItem.setQuantity(item.quantity());
            orderItem.setTotal(totalPriceProduct);
            orderItem.setOrder(order);
            orderItem.setCreatedAt(LocalDateTime.now());
            orderItem.setUpdatedAt(LocalDateTime.now());

            orderItemList.add(orderItemRepository.save(orderItem));
        }
        return orderItemList;
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

}
