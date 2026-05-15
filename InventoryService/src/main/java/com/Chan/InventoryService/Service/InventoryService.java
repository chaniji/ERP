package com.Chan.InventoryService.Service;

import com.Chan.InventoryService.DataTransferObjects.InventoryRequest;
import com.Chan.InventoryService.DataTransferObjects.InventoryResponse;
import com.Chan.InventoryService.Entity.Inventory;
import com.Chan.InventoryService.Exceptions.ResourceNotFoundException;
import com.Chan.InventoryService.Repository.InventoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository IRepo;

    public List<InventoryResponse> getAllInventory() {
        return IRepo.findAll().stream()
            .map(this::maptoResponse)
            .collect(Collectors.toList());
    }

    public InventoryResponse getInventoryById(String id) {
        Inventory I = IRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        return maptoResponse(I);
    }

    public InventoryResponse getInventoryByProductId(String productId) {
        Inventory I = IRepo.findByProductId(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with productId: " + productId));
        return maptoResponse(I);
    }

    public InventoryResponse createInventory(InventoryRequest request) {
        Inventory I = new Inventory();
        I.setProductId(request.getProductId());
        I.setProductName(request.getProductName());
        I.setQuantity(request.getQuantity());
        I.setPrice(request.getPrice());
        I.setLocation(request.getLocation());
        I.setInstock(request.getQuantity() > 0);
        Inventory saved = IRepo.save(I);
        return maptoResponse(saved);
    }

    public InventoryResponse updateInventory(String id, InventoryRequest request) {
        Inventory I = IRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        I.setProductName(request.getProductName());
        I.setQuantity(request.getQuantity());
        I.setPrice(request.getPrice());
        I.setLocation(request.getLocation());
        I.setInstock(request.getQuantity() > 0);
        Inventory updated = IRepo.save(I);
        return maptoResponse(updated);
    }

    public void deleteInventory(String id) {
        if (!IRepo.existsById(id)) {
            throw new ResourceNotFoundException("Inventory not found with id: " + id);
        }
        IRepo.deleteById(id);
    }

    public InventoryResponse updateStock(String id, int quantity) {
        Inventory I = IRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        I.setQuantity(quantity);
        I.setInstock(quantity > 0);
        Inventory updated = IRepo.save(I);
        return maptoResponse(updated);
    }

    public List<InventoryResponse> getLowStockItems() {
        final int LOW_STOCK_THRESHOLD = 10;
        return IRepo.findByQuantityLessThan(LOW_STOCK_THRESHOLD).stream()
            .map(this::maptoResponse)
            .collect(Collectors.toList());
    }

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
