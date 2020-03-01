package com.nagarro.shop.nagarroshopinventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.shop.nagarroshopinventoryservice.dao.InventoryItemDAO;
import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItem;

@Service
public class InventoryItemService {

	@Autowired
	private InventoryItemDAO inventoryItemDAO;
	
	public List<InventoryItem> getAllActiveItems() {
		return inventoryItemDAO.findByIsActiveTrue();
	}
}
