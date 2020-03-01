package com.nagarro.shop.nagarroshoporderservice.entity;

import java.util.List;

public class UserOrderHistory {

	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
