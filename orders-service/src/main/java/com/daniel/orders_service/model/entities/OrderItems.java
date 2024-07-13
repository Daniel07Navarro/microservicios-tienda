package com.daniel.orders_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sku;

    private Double price;

    private short quantity;

    @ManyToOne
    @JoinColumn(name= "order_id")
    private Order order;

}
