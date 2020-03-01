package com.nagarro.shop.nagarroshopuserservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.shop.nagarroshopuserservice.entity.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

}
