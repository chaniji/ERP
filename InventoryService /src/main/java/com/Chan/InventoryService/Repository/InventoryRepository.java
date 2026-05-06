package com.Chan.InventoryService.Repository;

import com.Chan.InventoryService.Entity.Inventory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository
    extends MongoRepository<Inventory, String>
{
    Optional<Inventory> findByProductId(String productId);
    boolean existsByProductId(String productId);
    List<Inventory> findByLocation(String location);
    List<Inventory> findByQuantityLessThan(int quantity);
    void deleteByProductId(String productId);
}
