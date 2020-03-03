package com.nagarro.shop.nagarroshopinventoryservice.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;

/**
 * TransactionalOutboxDAO
 */
@Repository
public interface OutboundMessageDAO extends CrudRepository<OutboundMessage, Long> {

    List<OutboundMessage> findByPublishedAtNullAndAcknowledgedAtNull();
    List<OutboundMessage> findByAcknowledgedAtNullAndCreationTimeBefore(ZonedDateTime createdBefore);
}