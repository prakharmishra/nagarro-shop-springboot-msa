package com.nagarro.shop.nagarroshoptrackingservice.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshoptrackingservice.entity.OutboundMessage;
import com.nagarro.shop.nagarroshoptrackingservice.entity.Track;

/**
 * TransactionalOutboxDAO
 */
@Repository
public interface TrackDAO extends CrudRepository<Track, Long> {

}