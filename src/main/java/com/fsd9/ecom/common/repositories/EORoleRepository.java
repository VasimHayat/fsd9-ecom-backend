package com.fsd9.ecom.common.repositories;

import com.fsd9.ecom.common.model.EORole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EORoleRepository extends JpaRepository<EORole,Long> {
    EORole findByName(String name);
    public Optional<EORole> findById(Long id);

    @Query("select e from EORole e where e.id =3")
    public EORole getUserRole();

    @Query("select e from EORole e where e.id =1")
    public EORole getAdminRole();
}


//https://bootify.io/app/SRT7SUP8UPUX