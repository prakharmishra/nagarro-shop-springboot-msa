package com.nagarro.shop.nagarroshopinventoryservice.domainevent;

import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;

/**
 * OrderReceivedEvent
 */
public class OrderReceivedEvent extends DomainEvent {

    public OrderReceivedEvent(OutboundMessage message) {
        super(message);
    }
}