package com.project.fitstore.dtos.token;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginCustomerRequest(@NotNull @NotBlank @Email String email, @NotNull @NotBlank String password) {
}
