package com.Chan.InventoryService.Entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventories")
public class Inventory {

    @Id
    private String id;

    @Field("product_id")
    private String productId;

    @Indexed(unique = true)
    @Field("product_name")
    private String productName;

    private int quantity;
    private double price;
    private String location;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("update_at")
    private LocalDateTime updatedAt;
}
