package com.project.fitstore.services;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.dtos.order.CreateOrderDto;
import com.project.fitstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    CustomerService customerService;

    public Order createOrder(CreateOrderDto createOrderDto, UUID customerId){
        checkIfCustomerExists(customerId);
        return orderRepository.save(createOrderDto.toOrder(customerId));
    }

    private void checkIfCustomerExists(UUID customerId){
        customerService.findCustomerById(customerId);
    }

}
