package com.nagarro.shop.nagarroshopinventoryservice.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.nagarro.shop.nagarroshopinventoryservice.model.OrderStatus;

@Entity
@Table(name = "processed_order")
public class ProcessedOrder extends BaseEntity {

	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "order_processed_status")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderProcessedStatus;
	
	@Column(name = "date_updated")
	private ZonedDateTime dateUpdated;

	@Override
	public String toString() {
		return "ProcessedOrder [orderId=" + orderId + ", orderProcessedStatus=" + orderProcessedStatus
				+ ", dateUpdated=" + dateUpdated + ", getId()=" + getId() + ", getCreationTime()=" + getCreationTime()
				+ "]";
	}

	public ProcessedOrder() {
	}

	public ProcessedOrder(Long orderId, OrderStatus orderProcessedStatus) {
		this.orderId = orderId;
		this.orderProcessedStatus = orderProcessedStatus;
		this.dateUpdated = null;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderProcessedStatus() {
		return orderProcessedStatus;
	}

	public void setOrderProcessedStatus(OrderStatus orderProcessedStatus) {
		this.orderProcessedStatus = orderProcessedStatus;
	}

	public ZonedDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(ZonedDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
