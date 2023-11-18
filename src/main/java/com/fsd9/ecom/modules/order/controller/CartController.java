package com.fsd9.ecom.modules.order.controller;

import com.fsd9.ecom.modules.order.dto.EOCartReqDto;
import com.fsd9.ecom.modules.order.dto.EOCartResDto;
import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.service.EOCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {


    @Autowired
    public EOCartService eoCartService;

    @PostMapping("/add")
    public EOCartResDto findAndCreateCart(@RequestBody EOCartReqDto reqDto) {
       try {
           EOCart cart = this.eoCartService.addCartItem(reqDto);
           return new EOCartResDto(cart);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    @PostMapping("/update")
    public EOCartResDto updateCartItem(@RequestBody EOCartReqDto reqDto) {
        try {
            EOCart cart =  this.eoCartService.updateCartItem(reqDto);
            return new EOCartResDto(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/delete")
    public EOCart deleteCartItem(@RequestBody EOCartReqDto reqDto) {
        return this.eoCartService.deleteCartItem(reqDto);
    }

}
