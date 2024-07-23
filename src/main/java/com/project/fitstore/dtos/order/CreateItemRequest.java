package com.project.fitstore.dtos.order;

import java.util.UUID;

public record CreateItemRequest(UUID id, Integer quantity) {
}
