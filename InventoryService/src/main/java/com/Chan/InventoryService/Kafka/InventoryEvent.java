package com.Chan.InventoryService.Kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private String id;
    private String type; // LOW_STOCK, CREATED, UPDATED
    private String message;
    private String email;
}
