package com.nagarro.shop.nagarroshoporderservice.dao;

import java.time.ZonedDateTime;
import java.util.List;

import com.nagarro.shop.nagarroshoporderservice.entity.OrderStatus;
import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionalOutboxDAO
 */
@Repository
public interface OutboundMessageDAO extends CrudRepository<OutboundMessage, Long> {

    List<OutboundMessage> findByPublishedAtNullAndAcknowledgedAtNull();
    List<OutboundMessage> findByAcknowledgedAtNullAndCreationTimeBefore(ZonedDateTime createdBefore);
}