package com.daniel.orders_service.model.dto;

public record OrderItemsResponse(Long id,String sku, Double price,Long quantity) {
}
