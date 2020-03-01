package com.nagarro.shop.nagarroshoporderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.shop.nagarroshoporderservice.entity.Order;
import com.nagarro.shop.nagarroshoporderservice.entity.UserOrderHistory;
import com.nagarro.shop.nagarroshoporderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> requestOrder(Order order) {
		Order savedOrder = orderService.save(order);
		if (savedOrder == null)
			return ResponseEntity.unprocessableEntity().body(order);
		
		return ResponseEntity.ok(savedOrder);
	}
	
	@GetMapping
	public UserOrderHistory getOrderHistory(Long userId) {
		UserOrderHistory userOrderHistory = new UserOrderHistory();
		userOrderHistory.setOrders(orderService.getAllOrdersByUserId(userId));
		
		return userOrderHistory;
	}
}
