package com.fsd9.ecom.modules.order.controller;

import com.fsd9.ecom.modules.order.dto.EOCartReqDto;
import com.fsd9.ecom.modules.order.dto.EOCartResDto;
import com.fsd9.ecom.modules.order.dto.EOUserOrderRepDto;
import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOOrder;
import com.fsd9.ecom.modules.order.service.EOCartService;
import com.fsd9.ecom.modules.order.service.EOOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {


    @Autowired
    public EOCartService eoCartService;

    @Autowired
    public EOOrderService eoOrderService;

    @PostMapping("/detail")
    public EOCartResDto cartDetail(@RequestBody EOCartReqDto reqDto) {
        try {
            EOCart cart = this.eoCartService.cartDetail(reqDto);
            return new EOCartResDto(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new EOCartResDto();
    }
    @PostMapping("/add")
    public EOCartResDto findAndCreateCart(@RequestBody EOCartReqDto reqDto) {
       try {
           EOCart cart = this.eoCartService.addCartItem(reqDto);
           return new EOCartResDto(cart);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new EOCartResDto();
    }

    @PostMapping("/update")
    public EOCartResDto updateCartItem(@RequestBody EOCartReqDto reqDto) {
        try {
            EOCart cart =  this.eoCartService.updateCartItem(reqDto);
            return new EOCartResDto(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new EOCartResDto();
    }

    @PostMapping("/delete")
    public EOCart deleteCartItem(@RequestBody EOCartReqDto reqDto) {
        return this.eoCartService.deleteCartItem(reqDto);
    }


    @PostMapping("/checkout")
    public EOUserOrderRepDto checkout(@RequestBody EOCartReqDto reqDto) {
        try {
            EOOrder eoOrder = this.eoOrderService.createOrder(reqDto);
            return new EOUserOrderRepDto(eoOrder);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new EOUserOrderRepDto();
    }
}
