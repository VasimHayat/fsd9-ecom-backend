package com.fsd9.ecom.modules.order.repositories;

import com.fsd9.ecom.modules.order.model.EOCart;
import com.fsd9.ecom.modules.order.model.EOCartItem;
import com.fsd9.ecom.modules.product.model.EOProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EOCartItemRepository extends JpaRepository<EOCartItem,Long> {

    @Query("SELECT ci FROM EOCartItem ci WHERE ci.eoCart.id = :eoCartID AND ci.eoProduct.id = :eoProductID")
    EOCartItem findByCartAndProduct(@Param("eoCartID") long eoCartID, @Param("eoProductID") long eoProductID);
}
