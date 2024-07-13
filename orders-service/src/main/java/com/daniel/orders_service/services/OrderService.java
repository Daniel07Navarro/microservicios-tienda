package com.daniel.orders_service.services;

import com.daniel.orders_service.model.dto.*;
import com.daniel.orders_service.model.entities.Order;
import com.daniel.orders_service.model.entities.OrderItems;
import com.daniel.orders_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){

        // VERIFICAR STOCK
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if(result != null && !result.hasErrors()){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest,order))
                    .toList());
            this.orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Some of the products are not in the stock");
        }
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order){
        return new OrderResponse(order.getId(),order.getOrderNumber() , order.getOrderItems().stream()
                .map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemsResponse mapToOrderItemRequest(OrderItems orderItems){
        return new OrderItemsResponse(orderItems.getId(),orderItems.getSku(),orderItems.getPrice(),orderItems.getQuantity());
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest,Order order){
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }

}
