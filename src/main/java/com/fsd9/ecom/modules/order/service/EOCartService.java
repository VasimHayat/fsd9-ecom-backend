package com.fsd9.ecom.modules.order.service;

import com.fsd9.ecom.modules.order.repositories.EOCartRepository;
import com.fsd9.ecom.modules.order.repositories.EOOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EOCartService {

    @Autowired
    public EOCartRepository eoCartRepository;

    @Autowired
    public EOOrderRepository eoOrderRepository;

}
