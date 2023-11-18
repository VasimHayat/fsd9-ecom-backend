package com.fsd9.ecom.modules.order.service;

import com.fsd9.ecom.modules.order.dto.EOCartReqDto;
import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOCartItem;
import com.fsd9.ecom.modules.order.repositories.EOCartItemRepository;
import com.fsd9.ecom.modules.order.repositories.EOCartRepository;
import com.fsd9.ecom.modules.order.repositories.EOOrderRepository;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.repositories.EOProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Service
public class EOCartService {

    @Autowired
    public EOCartRepository eoCartRepository;

    @Autowired
    public EOCartItemRepository eoCartItemRepository;

    @Autowired
    public EOOrderRepository eoOrderRepository;

    @Autowired
    public EOProductRepository eoProductRepository;
    @Transactional
    public EOCart addCartItem(EOCartReqDto eoCartReqDto) {
        EOCart eoCart = null;
        if (eoCartReqDto.getEoCartPK() == null) {
            eoCart = new EOCart();
            EOProduct eoProduct = this.eoProductRepository.findById(eoCartReqDto.getEoProductItemPK()).get();
            EOCartItem eoCartItem = EOCartItem.builder()
                    .eoCart(eoCart)
                    .eoProduct(eoProduct)
                    .quantity(eoCartReqDto.getQnt()).build();
            eoCart.setEoCartItemArray(Collections.singleton(eoCartItem));
            eoCart = this.eoCartRepository.save(eoCart);
        }else{
            eoCart = this.eoCartRepository.findById(eoCartReqDto.getEoCartPK()).get();
            EOProduct eoProduct = this.eoProductRepository.findById(eoCartReqDto.getEoProductItemPK()).get();
            EOCartItem eoCartItem = EOCartItem.builder()
                    .eoCart(eoCart)
                    .eoProduct(eoProduct)
                    .quantity(eoCartReqDto.getQnt()).build();
            eoCart.getEoCartItemArray().add(eoCartItem);
            eoCart = this.eoCartRepository.save(eoCart);
        }




        return eoCart;

    }

    @Transactional
    public EOCart updateCartItem(EOCartReqDto eoCartReqDto) {

        EOCartItem cartItem = this.eoCartItemRepository.findByCartAndProduct(eoCartReqDto.getEoCartPK(),eoCartReqDto.getEoProductItemPK());

        cartItem.setQuantity(eoCartReqDto.getQnt());
        this.eoCartItemRepository.save(cartItem);

        return cartItem.getEoCart();

    }

    @Transactional
    public EOCart deleteCartItem(EOCartReqDto eoCartReqDto) {

        EOCartItem cartItem = this.eoCartItemRepository.findByCartAndProduct(eoCartReqDto.getEoCartPK(),eoCartReqDto.getEoProductItemPK());
        EOCart eoCart = cartItem.getEoCart();
        this.eoCartItemRepository.delete(cartItem);
        return cartItem.getEoCart();

    }

}
