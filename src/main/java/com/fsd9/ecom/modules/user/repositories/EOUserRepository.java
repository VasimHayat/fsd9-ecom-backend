package com.fsd9.ecom.modules.user.repositories;

import com.fsd9.ecom.modules.user.model.EOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EOUserRepository extends JpaRepository<EOUser,Long> {


    public EOUser getUserByEmail(String email);
    public List<EOUser> findAll();
}
