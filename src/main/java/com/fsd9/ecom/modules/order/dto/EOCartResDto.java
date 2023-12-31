package com.fsd9.ecom.modules.order.dto;

import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOCartItem;
import com.fsd9.ecom.modules.product.model.EOProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EOCartResDto {
    private Long eoCartPK;

    private double totalAmount =0;
    private Set<EOCartItemResDto> eoCartItemArray = new LinkedHashSet<>();
    public EOCartResDto(EOCart eoCart){
       this.eoCartPK = eoCart.getId();
        eoCart.getEoCartItemArray().forEach(e->{
            totalAmount+=e.getEoProduct().getPrice() * e.getQuantity();
            this.eoCartItemArray.add(new EOCartItemResDto(e));
        });
   }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 class EOCartItemResDto {
    private Long id;
    private int quantity;

    private Long productId;

    private String productName;

    private String description;

    private double price;

    public EOCartItemResDto(EOCartItem eoCartItem){
        this.id = eoCartItem.getId();

        this.quantity = eoCartItem.getQuantity();
        EOProduct eoProduct = eoCartItem.getEoProduct();
        this.productId = eoProduct.getId();
        this.productName = eoProduct.getName();
        this.description = eoProduct.getDescription();
        this.price = eoProduct.getPrice();

    }

}

