package com.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.amazon.entity.OrderEntity;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	

}