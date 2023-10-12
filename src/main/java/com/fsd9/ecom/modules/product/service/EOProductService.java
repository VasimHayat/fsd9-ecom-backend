package com.fsd9.ecom.modules.product.service;

import com.fsd9.ecom.modules.product.dto.ProductReqDto;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.model.EOProductImg;
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

import java.util.*;

@Service
public class EOProductService {

    @Autowired
    private EOProductCategoryRepository productCategoryRepository;

    @Autowired
    private EOEOProductRepository productRepository;

    @Autowired
    private EOUserRepository userRepository;


    public List<EOProductCategory> getAllProdCategory() {
        return this.productCategoryRepository.findAll();
    }

    public List<EOProduct> getProductsByCategory(String id) {
      return this.productRepository.findByCategoryId(Long.valueOf(id));
    }
    public List<EOProduct> getAllProducts() {
        return this.productRepository.findAll();
    }

    public EOProduct findById(long id){
        return this.productRepository.findById(id).get();
    }
    public EOProductCategory findByUid(String uid) {
        return this.productCategoryRepository.findByUid(uid);
    }


    public EOProduct createProduct(ProductReqDto productReqDto){
        EOProductCategory category;
        Map<String,EOProductCategory> categoryMap = new HashMap<>();

        if(categoryMap.containsKey( productReqDto.getCategoryUID())){
            category = categoryMap.get( productReqDto.getCategoryUID());
        }else{
            category =  this.productCategoryRepository.findByUid( productReqDto.getCategoryUID());
        }
        Optional<EOUser> sellers;
        if(productReqDto.getSellerId() == null){
            sellers = this.userRepository.findById(1l);
        }else{
            sellers = this.userRepository.findById(Long.valueOf(productReqDto.getSellerId()));
        }

        EOUser eoUser = sellers.get();
        EOProduct eoProduct = productReqDto.buildEOProduct(category);
        eoProduct.setEoProductImgArray(productReqDto.getEoProductImgArray());
        for (EOProductImg productImg : eoProduct.getEoProductImgArray()) {
            productImg.setEoProduct(eoProduct);
        }

        eoProduct.setSellerUser(eoUser);
        this.productRepository.save(eoProduct);
        return eoProduct;
    }

}
