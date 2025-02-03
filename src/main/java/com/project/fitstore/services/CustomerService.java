package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.*;
import com.project.fitstore.exceptions.customer.CustomerImageNotFoundException;
import com.project.fitstore.exceptions.customer.CustomerNotFoundException;
import com.project.fitstore.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void deleteCustomer(UUID id) {
        var customer = this.findCustomerById(id);
        if (customer.getImagePath() != null) {
            imageService.deleteImage(customer.getImagePath());
        }
        customerRepository.delete(customer);
    }

    public String uploadCustomerImage(MultipartFile imageFile, UUID id) {
        var customer = findCustomerById(id);

        String imageName = imageService.uploadImage(imageFile);

        customer.setImagePath(imageName);
        customer.setUpdatedAt(LocalDateTime.now());
        saveCustomer(customer);

        return "Image uploaded successfully: " + imageName;
    }

    public String updateCustomerImage(MultipartFile imageFile, UUID id) {
        var customer = findCustomerById(id);
        String oldImage = customer.getImagePath();

        String newImage = imageService.updateImage(imageFile, oldImage);

        customer.setImagePath(newImage);
        customer.setUpdatedAt(LocalDateTime.now());
        saveCustomer(customer);

        return newImage + " added.";
    }

    @Transactional
    public String deleteCustomerImage(String fileName, UUID id) {
        var customer = findCustomerById(id);

        if(customer.getImagePath() == null) {
            throw new CustomerImageNotFoundException();
        }

        if (customer.getImagePath().equals(fileName)) {
            imageService.deleteImage(fileName);
            customer.setImagePath(null);
            customer.setUpdatedAt(LocalDateTime.now());
            saveCustomer(customer);
        }
        else {
            throw new CustomerImageNotFoundException("The image you are trying to delete is not your account current image.");
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

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
