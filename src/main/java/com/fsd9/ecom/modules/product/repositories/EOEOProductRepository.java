package com.fsd9.ecom.modules.product.repositories;

import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.user.model.EORole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EOEOProductRepository extends JpaRepository<EOProduct,Long> {

    EORole findByName(String name);

    public Optional<EOProduct> findById(Long id);

//    @Query("select e from EOProduct e where e.id =3")
//    public EORole getUserRole();


}


//https://bootify.io/app/SRT7SUP8UPUX