package com.fsd9.ecom.modules.order.controller;

import com.fsd9.ecom.modules.order.dto.EOCartReqDto;
import com.fsd9.ecom.modules.order.dto.EOCartResDto;
import com.fsd9.ecom.modules.order.dto.EOUserOrderRepDto;
import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOOrder;
import com.fsd9.ecom.modules.order.service.EOCartService;
import com.fsd9.ecom.modules.order.service.EOOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    @Autowired
    public EOOrderService eoOrderService;


    @PostMapping("/all")
    public Set<EOUserOrderRepDto> checkout(@RequestBody EOCartReqDto reqDto) {
        try {
            Set<EOOrder> eoOrders = this.eoOrderService.getUserOrders(reqDto);
            Set<EOUserOrderRepDto> orderRepDtos = new LinkedHashSet<>();
            for (EOOrder eoOrder : eoOrders) {
                orderRepDtos.add(new EOUserOrderRepDto(eoOrder));
            }
            return orderRepDtos;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptySet();
    }
}
