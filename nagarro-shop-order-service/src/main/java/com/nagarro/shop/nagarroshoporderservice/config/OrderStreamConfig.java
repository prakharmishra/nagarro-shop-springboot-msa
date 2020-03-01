package com.nagarro.shop.nagarroshoporderservice.config;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.shop.nagarroshoporderservice.domainevent.OrderReceivedEvent;
import com.nagarro.shop.nagarroshoporderservice.domainevent.OrderTimedOutEvent;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

/**
 * OrderStreamConfig
 */
@Configuration
public class OrderStreamConfig {

    @Bean
    public EmitterProcessor<OrderReceivedEvent> orderReceivedEventEmitter() {
        return EmitterProcessor.create();
    }

    @Bean
    public EmitterProcessor<OrderTimedOutEvent> orderTimedOutEventEmitter() {
        return EmitterProcessor.create();
    }

    @Bean
    public Supplier<Flux<OrderReceivedEvent>> orderReceived(
            @Autowired EmitterProcessor<OrderReceivedEvent> emitterProcessor) {
        return () -> emitterProcessor;
    }

    @Bean
    public Supplier<Flux<OrderTimedOutEvent>> orderTimedOut(
            @Autowired EmitterProcessor<OrderTimedOutEvent> emitterProcessor) {
        return () -> emitterProcessor;
    }
}