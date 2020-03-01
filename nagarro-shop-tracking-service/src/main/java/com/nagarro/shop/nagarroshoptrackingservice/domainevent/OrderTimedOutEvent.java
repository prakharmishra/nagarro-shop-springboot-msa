package com.nagarro.shop.nagarroshoptrackingservice.domainevent;

import com.nagarro.shop.nagarroshoptrackingservice.entity.OutboundMessage;

/**
 * OrderTimedOutEvent
 */
public class OrderTimedOutEvent extends DomainEvent {

    public OrderTimedOutEvent(OutboundMessage message) {
        super(message);
    }
}