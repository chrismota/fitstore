package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.CreateCustomerDto;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoDto;
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

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto){
        return customerRepository.save(createCustomerDto.toCustomer());
    }

    public Customer updateCustomerInfo(UUID id, UpdateCustomerInfoDto updateCustomerInfoDto){
        Customer customer = this.findCustomerById(id);

        customer.setName(updateCustomerInfoDto.name());
        customer.setAddress(updateCustomerInfoDto.address());
        customer.setEmail(updateCustomerInfoDto.email());
        customer.setCpf(updateCustomerInfoDto.cpf());
        customer.setPhoneNumber(updateCustomerInfoDto.phoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    public Customer getCustomer(UUID id){
        return this.findCustomerById(id);
    }

    public String deleteCustomer(UUID id){
        customerRepository.delete(this.findCustomerById(id));
        return "Customer deleted successfully.";
    }

    public Customer findCustomerById(UUID id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new RuntimeException("Customer not found");
    }
}
