package com.nagarro.shop.nagarroshoporderservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order extends BaseEntity {

    @Column(name = "inventory_item_id")
    private Long inventoryItemId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
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
