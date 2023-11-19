package com.fsd9.ecom.modules.order.service;

import com.fsd9.ecom.modules.order.dto.EOCartReqDto;
import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOCartItem;
import com.fsd9.ecom.modules.order.model.EOOrder;
import com.fsd9.ecom.modules.order.model.EOOrderItem;
import com.fsd9.ecom.modules.order.repositories.EOCartRepository;
import com.fsd9.ecom.modules.order.repositories.EOOrderItemRepository;
import com.fsd9.ecom.modules.order.repositories.EOOrderRepository;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.repositories.EOUserRepository;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class EOOrderService {

    @Autowired
    public EOOrderRepository eoOrderRepository;

    @Autowired
    public EOOrderItemRepository eoOrderItemRepository;

    @Autowired
    public EOUserRepository eoUserRepository;

    @Autowired
    public EOCartRepository eoCartRepository;

    @Transactional
    public EOOrder createOrder(EOCartReqDto eoCartReqDto) {

        EOOrder eoOrder = new EOOrder();
        EOCart eoCart = this.eoCartRepository.findById(eoCartReqDto.getEoCartPK()).get();
        EOUser eoUser = this.eoUserRepository.findById(eoCartReqDto.getEoUserPK()).get();
        eoOrder.setEoUser(eoUser);
        this.eoOrderRepository.save(eoOrder);

        Set<EOOrderItem> eoOrderItemArray = new LinkedHashSet<>();
        eoCart.getEoCartItemArray().forEach(e -> {
            EOOrderItem eoOrderItem = EOOrderItem.builder()
                    .eoOrder(eoOrder)
                    .eoProduct(e.getEoProduct())
                    .quantity(e.getQuantity())
                    .price(e.getEoProduct().getPrice()).build();
            eoOrderItemArray.add(eoOrderItem);
            this.eoOrderItemRepository.save(eoOrderItem);
        });

        eoOrder.setEoOrderItemArray(eoOrderItemArray);

        this.eoOrderRepository.save(eoOrder);

        this.eoCartRepository.delete(eoCart);

        return eoOrder;

    }

    @Transactional
    public Set<EOOrder> getUserOrders(EOCartReqDto eoCartReqDto) {
        return this.eoOrderRepository.allOrderByUser(eoCartReqDto.getEoUserPK());
    }
}
