package com.nagarro.shop.nagarroshoporderservice.domainevent;

import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

/**
 * OrderTimedOutEvent
 */
public class OrderTimedOutEvent extends DomainEvent {

    public OrderTimedOutEvent(OutboundMessage message) {
        super(message);
    }
}