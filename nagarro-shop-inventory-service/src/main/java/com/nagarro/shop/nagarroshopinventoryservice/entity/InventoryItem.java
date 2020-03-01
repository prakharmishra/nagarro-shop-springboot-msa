package com.nagarro.shop.nagarroshopinventoryservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class InventoryItem extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "price")
	private double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	private InventoryItemAvailability inventoryItemAvailability;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public InventoryItemAvailability getInventoryItemAvailability() {
		return inventoryItemAvailability;
	}

	public void setInventoryItemAvailability(InventoryItemAvailability inventoryItemAvailability) {
		this.inventoryItemAvailability = inventoryItemAvailability;
	}

	@Override
	public String toString() {
		return "InventoryItem [name=" + name + ", pictureUrl=" + pictureUrl + ", price=" + price + ", getId()="
				+ getId() + ", getCreationTime()=" + getCreationTime() + "]";
	}

	public InventoryItem(String name, String pictureUrl, double price) {
		super();
		this.name = name;
		this.pictureUrl = pictureUrl;
		this.price = price;
	}

	public InventoryItem() {
	}
}
