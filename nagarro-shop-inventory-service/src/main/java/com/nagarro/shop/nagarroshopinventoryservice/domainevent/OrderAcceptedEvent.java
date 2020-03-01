package com.nagarro.shop.nagarroshopinventoryservice.domainevent;

import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;

/**
 * OrderItemsAvailableEvent
 */
public class OrderAcceptedEvent extends DomainEvent {

    public OrderAcceptedEvent(OutboundMessage message) {
        super(message);
    }
}