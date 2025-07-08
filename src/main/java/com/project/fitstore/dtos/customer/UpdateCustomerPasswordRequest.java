package com.project.fitstore.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCustomerPasswordRequest(
        @NotNull(message = "current password is mandatory")
        @NotBlank(message = "current password cannot be blank")
        @Size(min = 6, message = "Current password must have at least 6 characters.")
        String currentPassword,
        @NotNull(message = "password is mandatory")
        @NotBlank(message = "password cannot be blank")
        @Size(min = 6, message = "Password must have at least 6 characters.")
        String newPassword) {
}
