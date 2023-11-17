package com.fsd9.ecom.modules.order.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDt {
  private Long UserId;
  private String OrderAddress;
  private String PhoneNumber;
  private double TotalAmount;
  private Set<OrderItemRequestDto> OrderItems;
}
