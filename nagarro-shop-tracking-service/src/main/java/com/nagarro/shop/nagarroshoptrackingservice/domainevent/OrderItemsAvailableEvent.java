package com.nagarro.shop.nagarroshoptrackingservice.domainevent;

import com.nagarro.shop.nagarroshoptrackingservice.entity.OutboundMessage;

/**
 * OrderItemsAvailableEvent
 */
public class OrderItemsAvailableEvent extends DomainEvent {

    public OrderItemsAvailableEvent(OutboundMessage message) {
        super(message);
    }
}