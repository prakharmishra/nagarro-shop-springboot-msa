package com.nagarro.shop.nagarroshopinventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.shop.nagarroshopinventoryservice.model.AvailableItems;
import com.nagarro.shop.nagarroshopinventoryservice.service.InventoryItemService;

@RestController
@RequestMapping("/catalogue")
public class ItemsForPurchaseController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	@GetMapping
	public AvailableItems getAvailableItems() {
		return new AvailableItems(inventoryItemService.getAllActiveItems());
	}
}
