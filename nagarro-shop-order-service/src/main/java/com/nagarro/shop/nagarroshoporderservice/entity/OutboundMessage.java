package com.nagarro.shop.nagarroshoporderservice.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * TransactionOutbox
 */
@Entity
@Table(name = "transactional_outbox")
public class OutboundMessage extends BaseEntity {

    @Column(name = "aggregate")
    @Enumerated(EnumType.STRING)
    private Aggregate aggregate;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(name = "message")
    private String message;

    @Column(name = "published_at")
    private ZonedDateTime publishedAt;

    @Column(name = "acknowledged_at")
    private ZonedDateTime acknowledgedAt;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(ZonedDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "OutboundMessage [aggregate=" + aggregate + ", message=" + message + ", operation=" + operation
                + ", publishedAt=" + publishedAt + "]";
    }

    public OutboundMessage() {
    }

    public OutboundMessage(Aggregate aggregate, Operation operation, String message) {
        this.aggregate = aggregate;
        this.operation = operation;
        this.message = message;
        this.publishedAt = null;
        this.acknowledgedAt = null;
    }

    public Aggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(Aggregate aggregate) {
        this.aggregate = aggregate;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public ZonedDateTime getAcknowledgedAt() {
        return acknowledgedAt;
    }

    public void setAcknowledgedAt(ZonedDateTime acknowledgedAt) {
        this.acknowledgedAt = acknowledgedAt;
    }
}