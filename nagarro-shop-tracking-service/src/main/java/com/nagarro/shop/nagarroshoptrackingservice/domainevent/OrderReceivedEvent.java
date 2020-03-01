package com.nagarro.shop.nagarroshoptrackingservice.domainevent;

import com.nagarro.shop.nagarroshoptrackingservice.entity.OutboundMessage;

/**
 * OrderReceivedEvent
 */
public class OrderReceivedEvent extends DomainEvent {

    public OrderReceivedEvent(OutboundMessage message) {
        super(message);
    }
}