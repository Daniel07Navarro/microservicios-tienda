package com.daniel.inventory_service.service;

import com.daniel.inventory_service.model.dto.BaseResponse;
import com.daniel.inventory_service.model.entities.Inventory;
import com.daniel.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.daniel.inventory_service.model.dto.OrderItemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity()>0).isPresent();
    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
        var errorList = new ArrayList<String>();
        List<String> skus = orderItems.stream()
                .map(OrderItemRequest::getSku)
                .toList();
        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if(inventory.isEmpty()){
                errorList.add("Product "+orderItem.getSku()+" does not exist");
            }else if(inventory.get().getQuantity()<orderItem.getQuantity()){
                errorList.add("Product "+orderItem.getSku()+" is out of stock");
            }
        });

        return !errorList.isEmpty() ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
