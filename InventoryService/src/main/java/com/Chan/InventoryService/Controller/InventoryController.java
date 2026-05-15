package com.Chan.InventoryService.Controller;

import com.Chan.InventoryService.DataTransferObjects.InventoryRequest;
import com.Chan.InventoryService.DataTransferObjects.InventoryResponse;
import com.Chan.InventoryService.DataTransferObjects.MessageResponse;
import com.Chan.InventoryService.Service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory Api", description = "CRUD operation for Inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Get all items in Inventory")
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Inventory by  id ")
    public ResponseEntity<InventoryResponse> getInventoryById(
        @PathVariable String id
    ) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get Inventory by Product id")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(
        @PathVariable String productId
    ) {
        return ResponseEntity.ok(
            inventoryService.getInventoryByProductId(productId)
        );
    }

    @PostMapping
    @Operation(summary = "Creating Products")
    public ResponseEntity<InventoryResponse> createInventory(
        @Valid @RequestBody InventoryRequest request
    ) {
        InventoryResponse created = inventoryService.createInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product by Id")
    public ResponseEntity<InventoryResponse> updateInventory(
        @PathVariable String id,
        @Valid @RequestBody InventoryRequest request
    ) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Product by Id")
    public ResponseEntity<MessageResponse> deleteInventory(
        @PathVariable String id
    ) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(
            new MessageResponse("Inventory deleted successfully")
        );
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Update a Stock By Id")
    public ResponseEntity<InventoryResponse> updateStock(
        @PathVariable String id,
        @RequestParam int quantity
    ) {
        return ResponseEntity.ok(inventoryService.updateStock(id, quantity));
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get a lowest Stocks")
    public ResponseEntity<List<InventoryResponse>> getLowStockItems() {
        return ResponseEntity.ok(inventoryService.getLowStockItems());
    }
}
