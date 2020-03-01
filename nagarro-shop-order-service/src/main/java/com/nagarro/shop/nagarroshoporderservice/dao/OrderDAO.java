package com.nagarro.shop.nagarroshoporderservice.dao;

import java.util.List;

import com.nagarro.shop.nagarroshoporderservice.entity.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderDAO
 */
@Repository
public interface OrderDAO extends CrudRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    
}