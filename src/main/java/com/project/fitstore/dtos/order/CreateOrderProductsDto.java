package com.project.fitstore.dtos.order;

import java.util.UUID;

public record CreateOrderProductsDto(UUID id, Integer quantity) {
}
