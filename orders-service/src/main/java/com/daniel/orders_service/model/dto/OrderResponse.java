package com.daniel.orders_service.model.dto;

import com.daniel.orders_service.model.entities.OrderItems;

import java.util.List;

public record OrderResponse(Long id, String orderNumber, List<OrderItemsResponse> orderItems) {
}
