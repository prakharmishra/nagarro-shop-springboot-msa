package com.nagarro.shop.nagarroshopinventoryservice.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderAcceptedEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderItemsNotAvailableEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderReceivedEvent;
import com.nagarro.shop.nagarroshopinventoryservice.domainevent.OrderTimedOutEvent;
import com.nagarro.shop.nagarroshopinventoryservice.model.Order;
import com.nagarro.shop.nagarroshopinventoryservice.service.InventoryItemAvailabilityService;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

/**
 * OrderStreamConfig
 */
@Configuration
public class OrderStreamConfig {
	
	private static final Logger log = LoggerFactory.getLogger(OrderStreamConfig.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private InventoryItemAvailabilityService availabilityService;

    @Bean
    public EmitterProcessor<OrderAcceptedEvent> orderAcceptedEventEmitter() {
        return EmitterProcessor.create();
    }

    @Bean
    public EmitterProcessor<OrderItemsNotAvailableEvent> orderItemsNotAvailableEventEmitter() {
        return EmitterProcessor.create();
    }

    @Bean
    public Supplier<Flux<OrderAcceptedEvent>> orderAccepted(
            @Autowired EmitterProcessor<OrderAcceptedEvent> emitterProcessor) {
        return () -> emitterProcessor;
    }

    @Bean
    public Supplier<Flux<OrderItemsNotAvailableEvent>> orderItemsNotAvailable(
            @Autowired EmitterProcessor<OrderItemsNotAvailableEvent> emitterProcessor) {
        return () -> emitterProcessor;
    }
    
    @Bean
    public Consumer<OrderReceivedEvent> processOrder() {
    	return receiveEvent -> {
    		try {
				availabilityService.processOrderReceived(objectMapper.readValue(receiveEvent.getMessage().getMessage(), Order.class));
			} catch (JsonProcessingException e) {
				log.error("Unable to parse Order from OrderReceivedEvent: " + receiveEvent.getMessage().getMessage());
			}
    	};
    }
    
    @Bean
    public Consumer<OrderTimedOutEvent> orderTimeOutEventProcessor() {
    	return event -> {
    		try {
				availabilityService.processOrderTimedOut(objectMapper.readValue(event.getMessage().getMessage(), Order.class));
			} catch (JsonProcessingException e) {
				log.error("Unable to parse Order from OrderTimedOutEvent: " + event.getMessage().getMessage());
			}
    	};
    }
}