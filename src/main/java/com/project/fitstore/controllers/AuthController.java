package com.project.fitstore.controllers;

import com.project.fitstore.dtos.token.JwtTokenResponse;
import com.project.fitstore.dtos.token.LoginCustomerRequest;
import com.project.fitstore.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> authenticateCustomer(@RequestBody LoginCustomerRequest loginCustomerRequest) {
        JwtTokenResponse token = authService.authenticateCustomer(loginCustomerRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}