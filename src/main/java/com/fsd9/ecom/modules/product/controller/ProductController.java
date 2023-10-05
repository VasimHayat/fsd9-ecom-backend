package com.fsd9.ecom.modules.product.controller;

import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.product.service.EOProductService;
import com.fsd9.ecom.modules.user.model.EOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private EOProductService eoProductService;

    @GetMapping(path = "/categories")
    public List<EOProductCategory> userList(){
        return this.eoProductService.getAllProdCategory();
    }

}
