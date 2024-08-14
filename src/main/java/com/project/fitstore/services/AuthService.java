package com.project.fitstore.services;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.token.JwtTokenResponse;
import com.project.fitstore.dtos.token.LoginCustomerRequest;
import com.project.fitstore.infra.security.JwtService;
import com.project.fitstore.infra.security.SecurityConfig;
import com.project.fitstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    final AuthenticationManager authenticationManager;
    final JwtService jwtService;
    final SecurityConfig securityConfig;
    final CustomerRepository customerRepository;

    public JwtTokenResponse authenticateCustomer(LoginCustomerRequest loginCustomerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginCustomerRequest.email(), loginCustomerRequest.password()));

        Customer customer = customerRepository.findByEmail(loginCustomerRequest.email()).orElseThrow();
        String token = jwtService.generateToken(customer);

        return new JwtTokenResponse(token);
    }
}
