package com.fsd9.ecom.modules.order.repositories;

import com.fsd9.ecom.modules.order.model.EOCartItem;
import com.fsd9.ecom.modules.order.model.EOOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EOOrderRepository extends JpaRepository<EOOrder,Long> {
    @Query("SELECT ci FROM EOOrder ci WHERE ci.eoUser.id = :eoUserId")
    Set<EOOrder> allOrderByUser(@Param("eoUserId") long eoUserId);
}
