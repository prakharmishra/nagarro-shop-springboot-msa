package com.nagarro.shop.nagarroshoporderservice.domainevent;

import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

/**
 * OrderReceivedEvent
 */
public class OrderReceivedEvent extends DomainEvent {

    public OrderReceivedEvent(OutboundMessage message) {
        super(message);
    }
}