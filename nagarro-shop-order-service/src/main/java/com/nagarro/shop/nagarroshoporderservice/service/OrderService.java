package com.nagarro.shop.nagarroshoporderservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.shop.nagarroshoporderservice.dao.OrderDAO;
import com.nagarro.shop.nagarroshoporderservice.dao.OutboundMessageDAO;
import com.nagarro.shop.nagarroshoporderservice.entity.Aggregate;
import com.nagarro.shop.nagarroshoporderservice.entity.Operation;
import com.nagarro.shop.nagarroshoporderservice.entity.Order;
import com.nagarro.shop.nagarroshoporderservice.entity.OrderStatus;
import com.nagarro.shop.nagarroshoporderservice.entity.OutboundMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderService
 */
@Service
public class OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OutboundMessageDAO outboxDAO;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MessageRelayService messageRelayService;

    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderDAO.findByUserId(userId);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderDAO.findById(id);
    }

    @Transactional
    public Order save(Order order) {

       try {
            String orderJson = objectMapper.writeValueAsString(order);

            orderDAO.save(order);

            OutboundMessage outbox = new OutboundMessage(Aggregate.ORDER, Operation.ORDER_RECEIVED, orderJson);
            outboxDAO.save(outbox);
            
            messageRelayService.relayEvents();
            
            return order;

        } catch (JsonProcessingException e) {
            log.error("Unable to convert " + order + " to JSON", e);
            return null;
        }
    }

    @Transactional
    public Optional<Order> updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Optional<Order> orderDB = orderDAO.findById(orderId);
        if (!orderDB.isPresent())
            return Optional.empty();

        if (orderDB.get().getStatus() == OrderStatus.DELIVERED ||
            orderDB.get().getStatus() == OrderStatus.ITEMS_NOT_AVAILABLE ||
            orderDB.get().getStatus() == OrderStatus.TIMED_OUT) {
                return orderDB;
            }

        if (newStatus == OrderStatus.ITEMS_NOT_AVAILABLE ||
            newStatus == OrderStatus.TIMED_OUT ||
            (orderDB.get().getStatus() == OrderStatus.PENDING && newStatus == OrderStatus.ACCEPTED) ||
            (orderDB.get().getStatus() == OrderStatus.ACCEPTED && newStatus == OrderStatus.OUT_FOR_DELIVERY) ||
            (orderDB.get().getStatus() == OrderStatus.OUT_FOR_DELIVERY && newStatus == OrderStatus.DELIVERED)) {
                orderDB.get().setStatus(newStatus);
                orderDAO.save(orderDB.get());

                return orderDB;
            }

        return orderDB;
    }
}
