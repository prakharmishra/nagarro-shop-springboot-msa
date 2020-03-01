package com.nagarro.shop.nagarroshopinventoryservice.model;

import java.util.List;

import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItem;

public class AvailableItems {

	private List<InventoryItem> availableItems;

	public List<InventoryItem> getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(List<InventoryItem> availableItems) {
		this.availableItems = availableItems;
	}

	@Override
	public String toString() {
		return "AvailableItems [availableItems=" + availableItems + "]";
	}

	public AvailableItems(List<InventoryItem> availableItems) {
		super();
		this.availableItems = availableItems;
	}
}
