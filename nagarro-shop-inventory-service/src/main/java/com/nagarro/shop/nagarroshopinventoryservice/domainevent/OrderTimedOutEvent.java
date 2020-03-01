package com.nagarro.shop.nagarroshopinventoryservice.domainevent;

import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;

/**
 * OrderTimedOutEvent
 */
public class OrderTimedOutEvent extends DomainEvent {

    public OrderTimedOutEvent(OutboundMessage message) {
        super(message);
    }
}