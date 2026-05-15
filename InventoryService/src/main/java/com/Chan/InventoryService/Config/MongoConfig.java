// src/main/java/com/Chan/InventoryService/Config/MongoConfig.java
package com.Chan.InventoryService.Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
            "mongodb://chan:1234@localhost:27017/inventorydb?authSource=admin"
        );
    }
}
