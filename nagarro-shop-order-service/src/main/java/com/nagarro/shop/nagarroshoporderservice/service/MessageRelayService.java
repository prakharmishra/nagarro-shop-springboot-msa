package com.nagarro.shop.nagarroshoporderservice.service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.nagarro.shop.nagarroshoporderservice.dao.OutboundMessageDAO;
import com.nagarro.shop.nagarroshoporderservice.domainevent.DomainEvent;
import com.nagarro.shop.nagarroshoporderservice.domainevent.OrderReceivedEvent;
import com.nagarro.shop.nagarroshoporderservice.domainevent.OrderTimedOutEvent;
import com.nagarro.shop.nagarroshoporderservice.entity.Aggregate;
import com.nagarro.shop.nagarroshoporderservice.entity.Operation;
import com.nagarro.shop.nagarroshoporderservice.entity.OrderStatus;
import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    private EmitterProcessor<OrderReceivedEvent> orderReceivedEventEmitter;

    @Autowired
    private EmitterProcessor<OrderTimedOutEvent> orderTimedOutEventEmitter;

    @Transactional
    @Scheduled(fixedDelayString = "${messagerelay.fixedratemillis}")
    public void relayEvents() {
    	final ZonedDateTime currentTime = ZonedDateTime.now(ZoneOffset.UTC);
    	
        handleTimedOutMessages(currentTime);
        handleMessagesToBeSent(currentTime);
    }

	private void handleMessagesToBeSent(ZonedDateTime currentTime) {
		List<OutboundMessage> messagesToBeSent = messageDAO.findByPublishedAtNullAndAcknowledgedAtNull();
		List<OutboundMessage> orderReceivedMessagesToBeSent = messagesToBeSent
				.stream()
				.filter(msg -> msg.getAggregate() == Aggregate.ORDER && msg.getOperation() == Operation.ORDER_RECEIVED)
				.collect(Collectors.toList());
		
		List<OutboundMessage> orderTimeoutMessagesToBeSent = messagesToBeSent
				.stream()
				.filter(msg -> msg.getAggregate() == Aggregate.ORDER && msg.getOperation() == Operation.ORDER_TIMED_OUT)
				.collect(Collectors.toList());
		
		orderReceivedMessagesToBeSent.stream().map(OrderReceivedEvent::new).forEach(orderReceivedEventEmitter::onNext);
		orderTimeoutMessagesToBeSent.stream().map(OrderTimedOutEvent::new).forEach(orderTimedOutEventEmitter::onNext);
		
		orderReceivedMessagesToBeSent.forEach(msg -> msg.setPublishedAt(currentTime));
		orderTimeoutMessagesToBeSent.forEach(msg -> msg.setPublishedAt(currentTime));
		
		messageDAO.saveAll(orderReceivedMessagesToBeSent);
		messageDAO.saveAll(orderTimeoutMessagesToBeSent);
	}

	private void handleTimedOutMessages(ZonedDateTime currentTime) {
        
        List<OutboundMessage> timedOutMessages = messageDAO.findByAcknowledgedAtNullAndCreatedAtBefore(currentTime.minus(timeoutMillis, ChronoUnit.MILLIS));

        List<OutboundMessage> timedOutOrderMessages = timedOutMessages
                .stream()
                .filter(msg -> msg.getAggregate() == Aggregate.ORDER && msg.getOperation() == Operation.ORDER_RECEIVED)
                .collect(Collectors.toList());

        List<OutboundMessage> timeOutOrderMessagesToBeSent = timedOutOrderMessages
        		.stream()
        		.map(msg -> new OutboundMessage(Aggregate.ORDER, Operation.ORDER_TIMED_OUT, msg.getMessage()))
        		.collect(Collectors.toList());
        
        timedOutOrderMessages.forEach(msg -> msg.setAcknowledgedAt(currentTime));
        
        messageDAO.saveAll(timeOutOrderMessagesToBeSent);
        messageDAO.saveAll(timedOutOrderMessages);
	}
}