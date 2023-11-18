package com.fsd9.ecom.modules.order.service;

import java.util.Set;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd9.ecom.modules.order.dto.OrderItemRequestDto;
import com.fsd9.ecom.modules.order.dto.OrderRequestDt;
import com.fsd9.ecom.modules.order.model.EOOrder;
import com.fsd9.ecom.modules.order.model.EOOrderItem;
import com.fsd9.ecom.modules.order.repository.OrderItemsRepository;
import com.fsd9.ecom.modules.order.repository.OrderRepository;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.service.EOProductService;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.service.EOUserService;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemsRepository orderItemsRepository;

  @Autowired
  private EOUserService eoUserService;

  @Autowired
  private EOProductService eoProductService;

  public EOOrder createOrder(OrderRequestDt Order){
    // Long UserId, String OrderAddress, String PhoneNumber, Double TotalAmount, Set<OrderItemRequestDto> OrderItems 
    Optional<EOUser> user = eoUserService.findById(Order.getUserId());

    if(user != null){
      EOOrder order = EOOrder.builder()
                      .address(Order.getOrderAddress())
                      .phoneNumber(Order.getPhoneNumber())
                      .totalAmount(Order.getTotalAmount())
                      .eoUser(user.get())
                      .orderDate(new Timestamp(new Date().getTime())).build();
      orderRepository.save(order);
      
      for(OrderItemRequestDto item: Order.getOrderItems()){
        EOProduct product = eoProductService.findById(item.getProductId());
        EOOrderItem orderItem = EOOrderItem.builder()
        .eoOrder(order)
        .eoProduct(product)
        .price(item.getPrice())
        .quantity(item.getQuantity()).build();
        orderItemsRepository.save(orderItem);
      }
      return order;
    }
    return null;
  }

  // public EOOrder deleteOrder(Long orderId){
  //   EOOrder order = orderRepository.findById(orderId).get();
  //   orderRepository.deleteById(orderId);
  //   return order;
  // }
}
