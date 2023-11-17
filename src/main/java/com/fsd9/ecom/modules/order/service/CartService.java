package com.fsd9.ecom.modules.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.repository.CartRepository;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;
  
}
