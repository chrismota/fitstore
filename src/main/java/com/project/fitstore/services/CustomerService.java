package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.*;
import com.project.fitstore.exceptions.customer.*;
import com.project.fitstore.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;
    final ImageService imageService;
    final PasswordEncoder passwordEncoder;

    public GetAllCustomersResponse getAllCustomers() {
        return GetAllCustomersResponse.from(customerRepository.findAll());
    }

    public GetCustomerResponse getCustomer(UUID id) {
        return GetCustomerResponse.from(findCustomerById(id));
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        String encodedPassword = passwordEncoder.encode(createCustomerRequest.password());
        Customer customer;
        try {
            customer = customerRepository.save(createCustomerRequest.toCustomer(encodedPassword));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateCustomerFieldException();
        }

        return CreateCustomerResponse.from(customer);
    }

    public UpdateCustomerResponse updateCustomerInfo(UUID id, UpdateCustomerInfoRequest updateCustomerInfoRequest) {
        Customer customer = this.findCustomerById(id);

        customer.setName(updateCustomerInfoRequest.name());
        customer.setStreet(updateCustomerInfoRequest.street());
        customer.setHouseNumber(updateCustomerInfoRequest.houseNumber());
        customer.setComplement(updateCustomerInfoRequest.complement());
        customer.setNeighborhood(updateCustomerInfoRequest.neighborhood());
        customer.setCity(updateCustomerInfoRequest.city());
        customer.setState(updateCustomerInfoRequest.state());
        customer.setCep(updateCustomerInfoRequest.cep());
        customer.setEmail(updateCustomerInfoRequest.email());
        customer.setCpf(updateCustomerInfoRequest.cpf());
        customer.setPhoneNumber(updateCustomerInfoRequest.phoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());

        try {
            customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateCustomerFieldException();
        }
        return UpdateCustomerResponse.from(customer);
    }

    public UpdateCustomerResponse updateCustomerPassword(UUID id, UpdateCustomerPasswordRequest
            updateCustomerPasswordRequest) {
        Customer customer = this.findCustomerById(id);

        String encodedPassword = passwordEncoder.encode(updateCustomerPasswordRequest.password());
        customer.setPassword(encodedPassword);
        String encodedNewPassword = passwordEncoder.encode(updateCustomerPasswordRequest.newPassword());

        customer.setPassword(encodedNewPassword);
        customer.setUpdatedAt(LocalDateTime.now());

        return UpdateCustomerResponse.from(customerRepository.save(customer));
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        Customer customer = this.findCustomerById(id);
        if (customer.getImagePath() != null) {
            imageService.deleteImage(customer.getImagePath());
        }
        customerRepository.delete(customer);
    }

    public UpdateCustomerResponse uploadCustomerImage(MultipartFile imageFile, UUID id) {
        Customer customer = findCustomerById(id);
        String oldImage = customer.getImagePath();

        String newImage = imageService.updateImage(imageFile, oldImage);

        customer.setImagePath(newImage);
        customer.setUpdatedAt(LocalDateTime.now());

        return UpdateCustomerResponse.from(customerRepository.save(customer));
    }

    @Transactional
    public String deleteCustomerImage(String fileName, UUID id) {
        Customer customer = findCustomerById(id);

        if (customer.getImagePath() == null) {
            throw new CustomerImageNotFoundException();
        }

        if (customer.getImagePath().equals(fileName)) {
            imageService.deleteImage(fileName);
            customer.setImagePath(null);
            customer.setUpdatedAt(LocalDateTime.now());
            saveCustomer(customer);
        } else {
            throw new CustomerImageNotFoundException(
                    "The image you are trying to delete is not your account current image.");
        }
        return fileName + " successfully deleted.";
    }

    public Customer findCustomerById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new CustomerNotFoundException();
    }

    public void checkIfCustomerExists(UUID customerId) {
        findCustomerById(customerId);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
