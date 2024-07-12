package com.project.fitstore.dtos.order;

import java.util.UUID;

public record CreateItemDto(UUID id, Integer quantity) {
}
