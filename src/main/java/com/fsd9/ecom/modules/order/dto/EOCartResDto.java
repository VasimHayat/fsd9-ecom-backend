package com.fsd9.ecom.modules.order.dto;

import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOCartItem;
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
    private Set<EOCartItemResDto> eoCartItemArray = new LinkedHashSet<>();
    public EOCartResDto(EOCart eoCart){
       this.eoCartPK = eoCart.getId();
        eoCart.getEoCartItemArray().forEach(e->{
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

    private String productName;

    public EOCartItemResDto(EOCartItem eoCartItem){
        this.id = eoCartItem.getId();
        this.quantity = eoCartItem.getQuantity();
        this.productName = eoCartItem.getEoProduct().getName();
    }

}

