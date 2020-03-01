package com.nagarro.shop.nagarroshoporderservice.domainevent;

import java.time.ZonedDateTime;

import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

/**
 * DomainEvent
 */
public class DomainEvent {

    private OutboundMessage message;

    public DomainEvent(OutboundMessage message) {
        this.message = message;
    }

    public OutboundMessage getMessage() {
        return message;
    }

    public void setMessage(OutboundMessage message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "DomainEvent [message=" + message + "]";
    }
}