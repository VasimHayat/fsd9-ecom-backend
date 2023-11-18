package com.fsd9.ecom.startup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd9.ecom.modules.product.dto.ProductReqDto;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.product.service.EOProductService;
import com.fsd9.ecom.modules.user.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.modules.user.model.EORole;
import com.fsd9.ecom.modules.user.repositories.EORoleRepository;
import com.fsd9.ecom.modules.user.service.EOUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultInitializer implements CommandLineRunner {

    @Autowired
    private final EORoleRepository roleRepository;

    @Autowired
    private  EOProductService productService;

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
    //    this.createDefaultRoles();
    //    this.createDefaultUsers();
    //    this.creteCategories();
    //    this.createProducts();

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
        ProductReqDto[] eoProducts;
        Map<String,EOProductCategory> categoryMap = new HashMap<>();

        try {
            eoProducts = objectMapper.readValue(file, ProductReqDto[].class);
            for (ProductReqDto productReqDto : eoProducts) {
                 this.productService.createProduct(productReqDto);
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
