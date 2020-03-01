package com.nagarro.shop.nagarroshopinventoryservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshopinventoryservice.entity.InventoryItemAvailability;

@Repository
public interface InventoryItemAvailabilityDAO extends CrudRepository<InventoryItemAvailability, Long> {

}
