package com.daniel.orders_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Integer id;

    private String sku;

    private Double price;

    private short quantity;

}
