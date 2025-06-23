package com.project.fitstore.dtos.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull(message = "status is mandatory")
        @NotBlank(message = "status cannot be blank")
        String status) {
}
