package com.nagarro.shop.nagarroshopinventoryservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshopinventoryservice.entity.ProcessedOrder;

@Repository
public interface ProcessedOrderDAO extends CrudRepository<ProcessedOrder, Long>{

	@Query("SELECT COUNT(p) FROM ProcessedOrder p WHERE p.orderId = ?1")
	Long countByOrderId(Long orderId);
	
	List<ProcessedOrder> findByOrderId(Long orderId);
	
}
