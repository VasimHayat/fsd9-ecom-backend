package com.fsd9.ecom.modules.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EOCartReqDto {

    private Long eoCartPK;
    private Long eoProductItemPK;
    private int qnt;
    private Long eoUserPK;

}
