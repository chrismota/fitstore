package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.customer.CreateCustomerDto;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoDto;
import com.project.fitstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto){
        Customer customer = new Customer(createCustomerDto);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerInfo(UUID id, UpdateCustomerInfoDto updateCustomerInfoDto){
        Customer customer = this.findCustomerById(id);

        customer.setName(updateCustomerInfoDto.name());
        customer.setAddress(updateCustomerInfoDto.address());
        customer.setEmail(updateCustomerInfoDto.email());
        customer.setCpf(updateCustomerInfoDto.cpf());
        customer.setPhoneNumber(updateCustomerInfoDto.phoneNumber());

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
