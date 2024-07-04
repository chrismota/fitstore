package com.project.fitstore.dtos.order;

import java.util.UUID;

public record CreateOrderProductsDto(UUID productId, Integer quantity) {
}
