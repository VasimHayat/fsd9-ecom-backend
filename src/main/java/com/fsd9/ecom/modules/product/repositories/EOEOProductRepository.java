package com.fsd9.ecom.modules.product.repositories;

import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.user.model.EORole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EOEOProductRepository extends JpaRepository<EOProduct,Long> {

    EOProduct findByName(String name);


    @Query("SELECT e FROM EOProduct e WHERE e.eoProductCategory.id = :catgId")
    List<EOProduct> findByCategoryId(@Param("catgId") long catgId);

    public Optional<EOProduct> findById(Long id);

}


//https://bootify.io/app/SRT7SUP8UPUX