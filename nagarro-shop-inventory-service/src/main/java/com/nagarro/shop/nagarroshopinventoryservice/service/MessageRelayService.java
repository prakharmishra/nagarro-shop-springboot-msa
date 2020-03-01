package com.nagarro.shop.nagarroshopinventoryservice.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nagarro.shop.nagarroshopinventoryservice.dao.OutboundMessageDAO;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderAcceptedEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderItemsNotAvailableEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderReceivedEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderTimedOutEvent;
import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;
import com.nagarro.shop.nagarroshopinventoryservice.model.Aggregate;
import com.nagarro.shop.nagarroshopinventoryservice.model.Operation;

import reactor.core.publisher.EmitterProcessor;

/**
 * MessageRelayService
 */
@Service
public class MessageRelayService {

    @Value("${messagerelay.timeoutmillis}")
    private long timeoutMillis;

    @Autowired
    private OutboundMessageDAO messageDAO;

    @Autowired
    private EmitterProcessor<OrderAcceptedEvent> orderAcceptedEventEmitter;

    @Autowired
    private EmitterProcessor<OrderItemsNotAvailableEvent> orderItemsNotAvailableEventEmitter;

    @Transactional
    @Scheduled(fixedDelayString = "${messagerelay.fixedratemillis}")
    public void relayEvents() {
        handleMessagesToBeSent();
    }

    //@Transactional
	public void handleMessagesToBeSent() {
		final ZonedDateTime currentTime = ZonedDateTime.now(ZoneOffset.UTC);
		
		List<OutboundMessage> messagesToBeSent = messageDAO.findByPublishedAtNullAndAcknowledgedAtNull();
		List<OutboundMessage> orderAcceptedMessagesToBeSent = messagesToBeSent
				.stream()
				.filter(msg -> msg.getAggregate() == Aggregate.ORDER && msg.getOperation() == Operation.ORDER_ACCEPTED)
				.collect(Collectors.toList());
		
		List<OutboundMessage> orderItemsNotAvailableMessagesToBeSent = messagesToBeSent
				.stream()
				.filter(msg -> msg.getAggregate() == Aggregate.ORDER && msg.getOperation() == Operation.ORDER_ITEMS_NOT_AVAILABLE)
				.collect(Collectors.toList());
		
		orderAcceptedMessagesToBeSent.stream().map(OrderAcceptedEvent::new).forEach(orderAcceptedEventEmitter::onNext);
		orderItemsNotAvailableMessagesToBeSent.stream().map(OrderItemsNotAvailableEvent::new).forEach(orderItemsNotAvailableEventEmitter::onNext);
		
		orderAcceptedMessagesToBeSent.forEach(msg -> msg.setPublishedAt(currentTime));
		orderItemsNotAvailableMessagesToBeSent.forEach(msg -> msg.setPublishedAt(currentTime));
		
		messageDAO.saveAll(orderAcceptedMessagesToBeSent);
		messageDAO.saveAll(orderItemsNotAvailableMessagesToBeSent);
	}
}