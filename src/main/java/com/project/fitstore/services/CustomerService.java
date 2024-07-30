package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.*;
import com.project.fitstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;

    public GetAllCustomersResponse getAllCustomers(){
        return GetAllCustomersResponse.from(customerRepository.findAll());
    }

    public GetCustomerResponse getCustomer(UUID id){
        return GetCustomerResponse.from(findCustomerById(id));
    }
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest){

        return CreateCustomerResponse.from(customerRepository.save(createCustomerRequest.toCustomer()));
    }

    public UpdateCustomerInfoResponse updateCustomerInfo(UUID id, UpdateCustomerInfoRequest updateCustomerInfoRequest){
        Customer customer = this.findCustomerById(id);

        customer.setName(updateCustomerInfoRequest.name());
        customer.setAddress(updateCustomerInfoRequest.address());
        customer.setEmail(updateCustomerInfoRequest.email());
        customer.setCpf(updateCustomerInfoRequest.cpf());
        customer.setPhoneNumber(updateCustomerInfoRequest.phoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());

        return UpdateCustomerInfoResponse.from(customerRepository.save(customer));
    }

    public void deleteCustomer(UUID id){
        customerRepository.delete(this.findCustomerById(id));
    }

    public Customer findCustomerById(UUID id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new RuntimeException("Customer not found");
    }
}
