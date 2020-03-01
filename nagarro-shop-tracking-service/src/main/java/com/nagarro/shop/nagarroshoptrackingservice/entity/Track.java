package com.nagarro.shop.nagarroshoptrackingservice.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track extends OutboundMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "track_time")
	private ZonedDateTime trackTime = ZonedDateTime.now(ZoneOffset.UTC);
	
	public Track(OutboundMessage message) {
		this.setId(message.getId());
		this.setCreationTime(message.getCreationTime());
		this.setVersion(message.getVersion());
		
		this.setAggregate(message.getAggregate());
        this.setOperation(message.getOperation());
        this.setMessage(message.getMessage());
        this.setPublishedAt(message.getPublishedAt());
        this.setAcknowledgedAt(message.getAcknowledgedAt());
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public ZonedDateTime getTrackTime() {
		return trackTime;
	}

	public void setTrackTime(ZonedDateTime trackTime) {
		this.trackTime = trackTime;
	}
}
