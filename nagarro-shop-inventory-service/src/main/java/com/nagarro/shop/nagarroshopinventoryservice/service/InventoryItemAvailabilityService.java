package com.nagarro.shop.nagarroshopinventoryservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.shop.nagarroshopinventoryservice.dao.InventoryItemAvailabilityDAO;
import com.nagarro.shop.nagarroshopinventoryservice.dao.InventoryItemDAO;
import com.nagarro.shop.nagarroshopinventoryservice.dao.OutboundMessageDAO;
import com.nagarro.shop.nagarroshopinventoryservice.dao.ProcessedOrderDAO;
import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItem;
import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItemAvailability;
import com.nagarro.shop.nagarroshopinventoryservice.entity.OutboundMessage;
import com.nagarro.shop.nagarroshopinventoryservice.entity.ProcessedOrder;
import com.nagarro.shop.nagarroshopinventoryservice.model.Aggregate;
import com.nagarro.shop.nagarroshopinventoryservice.model.Operation;
import com.nagarro.shop.nagarroshopinventoryservice.model.Order;
import com.nagarro.shop.nagarroshopinventoryservice.model.OrderStatus;

@Service
public class InventoryItemAvailabilityService {
	
	private static final Logger log = LoggerFactory.getLogger(InventoryItemAvailabilityService.class);

	@Autowired
	private InventoryItemAvailabilityDAO availabilityDAO;
	
	@Autowired
	private InventoryItemDAO inventoryItemDAO;
	
	@Autowired
	private OutboundMessageDAO messageDAO;
	
	@Autowired
	private ProcessedOrderDAO processedOrderDAO;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MessageRelayService messageRelayService;
	
	public Iterable<InventoryItemAvailability> getAllItemsAvailable() {
		return availabilityDAO.findAll();
	}
	
	@Transactional
	public void processOrderReceived(Order order) {
		try {
			String orderJson = objectMapper.writeValueAsString(order);

			// Verify that order is not processed in the past (Idempotent behavior)
			if (processedOrderDAO.countByOrderId(order.getId()) == 0) {
				List<InventoryItem> inventoryItem = inventoryItemDAO.findActiveAvailableItem(order.getInventoryItemId());
				
				if (inventoryItem != null && inventoryItem.size() > 0) {
					Long noOfItemsAvailable = inventoryItem.get(0).getInventoryItemAvailability().getNoOfItemsAvailable() - 1;
					inventoryItem.get(0).getInventoryItemAvailability().setNoOfItemsAvailable(noOfItemsAvailable);
					
					inventoryItemDAO.save(inventoryItem.get(0));
					
					ProcessedOrder processedOrder = new ProcessedOrder(order.getId(), OrderStatus.ACCEPTED);
					processedOrderDAO.save(processedOrder);
					
					OutboundMessage message = new OutboundMessage(Aggregate.ORDER, Operation.ORDER_ACCEPTED, orderJson);
					messageDAO.save(message);
				} else {
					ProcessedOrder processedOrder = new ProcessedOrder(order.getId(), OrderStatus.ITEMS_NOT_AVAILABLE);
					processedOrderDAO.save(processedOrder);
					
					OutboundMessage message = new OutboundMessage(Aggregate.ORDER, Operation.ORDER_ITEMS_NOT_AVAILABLE, orderJson);
					messageDAO.save(message);
					
					messageRelayService.relayEvents();
				}
			}
		} catch (JsonProcessingException e) {
			log.error("Unable to convert " + order + " to JSON", e);
		}
	}
	
	@Transactional
	public void processOrderTimedOut(Order order) {
		Optional<ProcessedOrder> processedOrder = processedOrderDAO.findByOrderId(order.getId()).stream().findAny();
		
		// Verify that order is not marked TIMED_OUT in the past (Idempotent behavior)
		if (!processedOrder.isPresent() || processedOrder.get().getOrderProcessedStatus() != OrderStatus.TIMED_OUT) {
			List<InventoryItem> inventoryItem = inventoryItemDAO.findActiveAvailableItem(order.getInventoryItemId());
			
			if (inventoryItem != null && processedOrder.isPresent() && processedOrder.get().getOrderProcessedStatus() == OrderStatus.ACCEPTED) {
				Long noOfItemsAvailable = inventoryItem.get(0).getInventoryItemAvailability().getNoOfItemsAvailable() + 1;
				inventoryItem.get(0).getInventoryItemAvailability().setNoOfItemsAvailable(noOfItemsAvailable);
				
				inventoryItemDAO.save(inventoryItem.get(0));
			}
				
			if (processedOrder.isPresent()) {
				processedOrder.get().setOrderProcessedStatus(OrderStatus.TIMED_OUT);
			}
			
			processedOrderDAO.save(processedOrder.orElseGet(() -> new ProcessedOrder(order.getId(), OrderStatus.TIMED_OUT)));
		}
	}
}
