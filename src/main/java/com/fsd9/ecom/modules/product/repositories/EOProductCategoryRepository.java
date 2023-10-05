package com.fsd9.ecom.modules.product.repositories;

import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.user.model.EORole;
import com.fsd9.ecom.modules.user.model.EOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EOProductCategoryRepository extends JpaRepository<EOProductCategory,Long> {

    EORole findByName(String name);

    public Optional<EOProductCategory> findById(Long id);

    public List<EOProductCategory> findAll();

//    @Query("select e from EOProduct e where e.id =3")
//    public EORole getUserRole();


}