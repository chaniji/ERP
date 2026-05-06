package com.Chan.InventoryService.DataTransferObjects;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InventoryResponse {

    private String id;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean inStock;
}
