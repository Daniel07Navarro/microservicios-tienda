package com.daniel.inventory_service.controller;

import com.daniel.inventory_service.model.dto.BaseResponse;
import com.daniel.inventory_service.model.dto.OrderItemRequest;
import com.daniel.inventory_service.model.entities.Inventory;
import com.daniel.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public boolean isInStock(@RequestParam("sku") String sku) {
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    public ResponseEntity<BaseResponse> areInStock(@RequestBody List<OrderItemRequest> orderItems) {
        return new ResponseEntity<>(inventoryService.areInStock(orderItems), HttpStatus.OK);
    }

}
