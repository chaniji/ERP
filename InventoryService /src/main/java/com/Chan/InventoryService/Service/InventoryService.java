package com.Chan.InventoryService.Service;

import com.Chan.InventoryService.DataTransferObjects.InventoryResponse;
import com.Chan.InventoryService.Entity.Inventory;
import com.Chan.InventoryService.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository IRepo;



    private InventoryResponse maptoResponse(Inventory I) {
        return new InventoryResponse(
            I.getId(),
            I.getProductId(),
            I.getProductName(),
            I.getQuantity(),
            I.getPrice(),
            I.getLocation(),
            I.getCreatedAt(),
            I.getUpdatedAt(),
            I.getInstock()
        );
    }
}
