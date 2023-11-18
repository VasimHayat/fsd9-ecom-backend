package com.fsd9.ecom.modules.order.repositories;

import com.fsd9.ecom.modules.order.model.EOCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EOCartRepository extends JpaRepository<EOCart,Long> {

}
