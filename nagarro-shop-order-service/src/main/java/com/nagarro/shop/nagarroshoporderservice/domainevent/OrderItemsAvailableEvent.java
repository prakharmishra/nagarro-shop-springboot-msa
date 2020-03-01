package com.nagarro.shop.nagarroshoporderservice.domainevent;

import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

/**
 * OrderItemsAvailableEvent
 */
public class OrderItemsAvailableEvent extends DomainEvent {

    public OrderItemsAvailableEvent(OutboundMessage message) {
        super(message);
    }
}