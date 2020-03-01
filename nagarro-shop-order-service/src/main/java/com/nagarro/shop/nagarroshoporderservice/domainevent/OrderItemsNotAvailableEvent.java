package com.nagarro.shop.nagarroshoporderservice.domainevent;

import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

/**
 * OrderItemsNotAvailableEvent
 */
public class OrderItemsNotAvailableEvent extends DomainEvent {

    public OrderItemsNotAvailableEvent(OutboundMessage message) {
        super(message);
    }
}