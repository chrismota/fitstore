package com.project.fitstore.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.project.fitstore.domain.product.Product;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.CreateCustomerRequest;
import com.project.fitstore.dtos.customer.CreateCustomerResponse;
import com.project.fitstore.dtos.customer.GetAllCustomersResponse;
import com.project.fitstore.dtos.customer.GetCustomerResponse;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoRequest;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoResponse;
import com.project.fitstore.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;

    public GetAllCustomersResponse getAllCustomers() {
        return GetAllCustomersResponse.from(customerRepository.findAll());
    }

    public GetCustomerResponse getCustomer(UUID id) {
        return GetCustomerResponse.from(findCustomerById(id));
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        String encodedPassword = passwordEncoder.encode(createCustomerRequest.password());
        return CreateCustomerResponse.from(customerRepository.save(createCustomerRequest.toCustomer(encodedPassword)));
    }

    public UpdateCustomerInfoResponse updateCustomerInfo(UUID id, UpdateCustomerInfoRequest updateCustomerInfoRequest) {
        Customer customer = this.findCustomerById(id);

        customer.setName(updateCustomerInfoRequest.name());
        customer.setAddress(updateCustomerInfoRequest.address());
        customer.setEmail(updateCustomerInfoRequest.email());
        customer.setCpf(updateCustomerInfoRequest.cpf());
        customer.setPhoneNumber(updateCustomerInfoRequest.phoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());

        return UpdateCustomerInfoResponse.from(customerRepository.save(customer));
    }

    public void deleteCustomer(UUID id) {
        customerRepository.delete(this.findCustomerById(id));
    }

    public Customer findCustomerById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new RuntimeException("Customer not found");
    }

    public Customer findCustomerByImage(String image) {
        Optional<Customer> customer = customerRepository.findCustomerByImagePath(image);
        return customer.orElse(null);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
