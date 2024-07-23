package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Status;

public record UpdateOrderStatusRequest(Status status) {
}
