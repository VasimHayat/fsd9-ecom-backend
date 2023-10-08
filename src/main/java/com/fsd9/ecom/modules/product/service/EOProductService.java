package com.fsd9.ecom.modules.product.service;

import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOEOProductRepository;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.user.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.model.EOUserRole;
import com.fsd9.ecom.modules.user.repositories.EORoleRepository;
import com.fsd9.ecom.modules.user.repositories.EOUserRepository;
import com.fsd9.ecom.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EOProductService {

    @Autowired
    private EOProductCategoryRepository productCategoryRepository;

    @Autowired
    private EOEOProductRepository productRepository;


    public List<EOProductCategory> getAllProdCategory() {
        return this.productCategoryRepository.findAll();
    }


    public EOProductCategory findByUid(String uid) {
        return this.productCategoryRepository.findByUid(uid);
    }


}
