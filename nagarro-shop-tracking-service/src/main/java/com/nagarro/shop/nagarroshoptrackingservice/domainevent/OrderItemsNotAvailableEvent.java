package com.nagarro.shop.nagarroshoptrackingservice.domainevent;

import com.nagarro.shop.nagarroshoptrackingservice.entity.OutboundMessage;

/**
 * OrderItemsNotAvailableEvent
 */
public class OrderItemsNotAvailableEvent extends DomainEvent {

    public OrderItemsNotAvailableEvent(OutboundMessage message) {
        super(message);
    }
}