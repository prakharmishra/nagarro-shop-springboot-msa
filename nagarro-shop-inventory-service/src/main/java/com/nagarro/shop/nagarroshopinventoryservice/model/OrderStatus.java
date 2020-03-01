package com.nagarro.shop.nagarroshopinventoryservice.model;

/**
 * Status
 */
public enum OrderStatus {
    PENDING,
    TIMED_OUT,
    ITEMS_NOT_AVAILABLE,
    ACCEPTED,
    OUT_FOR_DELIVERY,
    DELIVERED
}