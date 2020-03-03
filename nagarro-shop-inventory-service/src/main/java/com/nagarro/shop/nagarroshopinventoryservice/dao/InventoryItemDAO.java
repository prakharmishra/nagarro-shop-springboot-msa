package com.nagarro.shop.nagarroshopinventoryservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItem;

@Repository
public interface InventoryItemDAO extends CrudRepository<InventoryItem, Long> {

	@Query("SELECT item FROM InventoryItem item JOIN item.inventoryItemAvailability avail WHERE item.id = ?1 AND avail.isActive = true AND avail.noOfItemsAvailable > 0")
	List<InventoryItem> findActiveAvailableItem(Long itemId);
	
	@Query("SELECT item FROM InventoryItem item JOIN item.inventoryItemAvailability avail WHERE avail.isActive = true")
	List<InventoryItem> findByIsActiveTrue();
}
