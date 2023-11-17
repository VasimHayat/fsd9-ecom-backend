package com.fsd9.ecom.modules.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd9.ecom.modules.order.dto.OrderRequestDt;
import com.fsd9.ecom.modules.order.model.EOOrder;
import com.fsd9.ecom.modules.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("")
  public ResponseEntity<EOOrder> createOrder(@RequestBody OrderRequestDt reqBody){
    EOOrder order =  orderService.createOrder(reqBody);
    return new ResponseEntity<EOOrder>(order, HttpStatus.OK);
  }
}
