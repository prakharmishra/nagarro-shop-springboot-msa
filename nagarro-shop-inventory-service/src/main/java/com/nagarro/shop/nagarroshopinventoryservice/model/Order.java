package com.nagarro.shop.nagarroshopinventoryservice.model;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Order {
	
    private Long id;

    private Long version;

    private ZonedDateTime creationTime = ZonedDateTime.now(ZoneOffset.UTC);

    private Long inventoryItemId;

    private Long userId;

    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", version=" + version + ", creationTime=" + creationTime + ", inventoryItemId="
				+ inventoryItemId + ", userId=" + userId + ", status=" + status + "]";
	}

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
