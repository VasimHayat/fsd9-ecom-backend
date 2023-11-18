package com.fsd9.ecom.modules.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsd9.ecom.modules.order.model.EOCart;

public interface CartRepository extends JpaRepository<EOCart, Long>{
  
}
