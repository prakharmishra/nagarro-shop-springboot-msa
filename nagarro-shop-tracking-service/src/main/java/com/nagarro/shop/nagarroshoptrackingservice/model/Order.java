package com.nagarro.shop.nagarroshoptrackingservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.nagarro.shop.nagarroshoptrackingservice.entity.BaseEntity;

public class Order extends BaseEntity {

    private Long inventoryItemId;

    private Long userId;

    private OrderStatus status;

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

    @Override
    public String toString() {
        return "Order [inventoryItemId=" + inventoryItemId + ", status=" + status + ", userId=" + userId + "]";
    }

    public Order() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order(Long inventoryItemId, Long userId) {
        this.inventoryItemId = inventoryItemId;
        this.userId = userId;
        this.status = OrderStatus.PENDING;
    }
}
