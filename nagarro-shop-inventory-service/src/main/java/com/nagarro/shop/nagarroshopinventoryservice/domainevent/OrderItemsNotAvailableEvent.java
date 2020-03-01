package com.nagarro.shop.nagarroshopinventoryservice.domainevent;

import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;

/**
 * OrderItemsNotAvailableEvent
 */
public class OrderItemsNotAvailableEvent extends DomainEvent {

    public OrderItemsNotAvailableEvent(OutboundMessage message) {
        super(message);
    }
}