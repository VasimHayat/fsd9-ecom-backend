package com.fsd9.ecom.startup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOEOProductRepository;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.user.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.modules.user.model.EORole;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.repositories.EORoleRepository;
import com.fsd9.ecom.modules.user.service.EOUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DefaultInitializer implements CommandLineRunner {

    @Autowired
    private final EORoleRepository roleRepository;

    @Autowired
    private EOEOProductRepository productRepository;

    @Autowired
    private EOUserService eoUserService;

    @Autowired
    private final  EOProductCategoryRepository eoProductCategoryRepository;


    public DefaultInitializer(EORoleRepository roleRepository,EOProductCategoryRepository prodCatgRepo) {
        this.roleRepository = roleRepository;
        this.eoProductCategoryRepository = prodCatgRepo;
    }

    @Override
    public void run(String... args) {
//       this.createDefaultRoles();
//        this.createDefaultUsers();
//        this.creteCategories();
//        this.createProducts();

    }

    private void createDefaultUsers(){
        File file = new File("src/main/resources/default_data/users.json");
        ObjectMapper objectMapper = new ObjectMapper();
        UserRegisterReqDto [] reqDtos;

        try {
            reqDtos = objectMapper.readValue(file,  UserRegisterReqDto[].class);
            // Save to Database
            for (UserRegisterReqDto regReqDto : reqDtos) {
                 this.eoUserService.createNewUser(regReqDto);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProducts(){
        File file = new File("src/main/resources/default_data/products.json");
        ObjectMapper objectMapper = new ObjectMapper();
        ProductBean[] eoProducts;
        Map<String,EOProductCategory> categoryMap = new HashMap<>();

        try {
            eoProducts = objectMapper.readValue(file, ProductBean[].class);
            // Save to Database
            for (ProductBean productBean : eoProducts) {
                EOProductCategory category;
                if(categoryMap.containsKey( productBean.getCategoryUID())){
                    category = categoryMap.get( productBean.getCategoryUID());
                }else{
                    category =  this.eoProductCategoryRepository.findByUid( productBean.getCategoryUID());
                }
                Optional<EOUser> sellers;
                if(productBean.getSellerId() == null){
                    sellers = this.eoUserService.findById(1l);
                }else{
                    sellers = this.eoUserService.findById(Long.valueOf(productBean.getSellerId()));
                }

                EOUser eoUser = sellers.get();
                EOProduct eoProduct = productBean.buildEOProduct(category);
                eoProduct.setEoProductImgArray(productBean.getEoProductImgArray());
                eoProduct.setSellerUser(eoUser);
                System.out.println(eoProduct.toString());
                this.productRepository.save(eoProduct);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void creteCategories(){
        File file = new File("src/main/resources/default_data/categories.json");
        ObjectMapper objectMapper = new ObjectMapper();
        EOProductCategory[] productCategories;
        try {
            productCategories = objectMapper.readValue(file, EOProductCategory[].class);
            // Save to Database
            for (EOProductCategory category : productCategories) {
                System.out.println(category.toString());
                this.eoProductCategoryRepository.save(category);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createDefaultRoles() {
        // Check if the default roles already exist


        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            EORole role = new EORole();
            role.setId(1l);
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_SELLER") == null) {
            EORole role = new EORole();
            role.setId(2l);
            role.setName("ROLE_SELLER");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            EORole role = new EORole();
            role.setId(3l);
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_SUPPORT_USER") == null) {
            EORole role = new EORole();
            role.setId(4l);
            role.setName("ROLE_SUPPORT_USER");
            roleRepository.save(role);
        }
    }
}
