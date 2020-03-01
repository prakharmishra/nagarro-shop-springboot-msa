package com.nagarro.shop.nagarroshoptrackingservice.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.shop.nagarroshoptrackingservice.domainevent.OrderItemsAvailableEvent;
import com.nagarro.shop.nagarroshoptrackingservice.domainevent.OrderItemsNotAvailableEvent;
import com.nagarro.shop.nagarroshoptrackingservice.domainevent.OrderReceivedEvent;
import com.nagarro.shop.nagarroshoptrackingservice.domainevent.OrderTimedOutEvent;
import com.nagarro.shop.nagarroshoptrackingservice.entity.Track;
import com.nagarro.shop.nagarroshoptrackingservice.service.TrackService;

/**
 * OrderStreamConfig
 */
@Configuration
public class OrderStreamConfig {

	@Autowired
	private TrackService trackService;
	
    @Bean
    public Consumer<OrderItemsAvailableEvent> orderAccepted() {
        return event -> trackService.save(new Track(event.getMessage()));
    }

    @Bean
    public Consumer<OrderItemsNotAvailableEvent> orderItemsNotAvailable() {
    	return event -> trackService.save(new Track(event.getMessage()));
    }
    
    @Bean
    public Consumer<OrderReceivedEvent> orderReceived() {
    	return event -> trackService.save(new Track(event.getMessage()));
    }

    @Bean
    public Consumer<OrderTimedOutEvent> orderTimedOut() {
    	return event -> trackService.save(new Track(event.getMessage()));
    }
}