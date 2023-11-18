package com.fsd9.ecom.modules.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsd9.ecom.modules.order.model.EOOrderItem;

public interface OrderItemsRepository extends JpaRepository<EOOrderItem, Long>{
  
}
