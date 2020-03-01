package com.nagarro.shop.nagarroshopinventoryservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_item_availablity")
public class InventoryItemAvailability extends BaseEntity {

	@OneToOne(mappedBy = "inventoryItemAvailability", cascade = CascadeType.ALL)
	private InventoryItem inventoryItem;
	
	@Column(name = "no_of_items_available")
	private Long noOfItemsAvailable;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public Long getNoOfItemsAvailable() {
		return noOfItemsAvailable;
	}

	public void setNoOfItemsAvailable(Long noOfItemsAvailable) {
		this.noOfItemsAvailable = noOfItemsAvailable;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public InventoryItemAvailability(InventoryItem inventoryItem, Long noOfItemsAvailable, Boolean isActive) {
		this.inventoryItem = inventoryItem;
		this.noOfItemsAvailable = noOfItemsAvailable;
		this.isActive = isActive;
	}

	public InventoryItemAvailability() {
	}

	@Override
	public String toString() {
		return "InventoryItemAvailability [inventoryItem=" + inventoryItem + ", noOfItemsAvailable="
				+ noOfItemsAvailable + ", isActive=" + isActive + ", getId()=" + getId() + ", getCreationTime()="
				+ getCreationTime() + "]";
	}
}
