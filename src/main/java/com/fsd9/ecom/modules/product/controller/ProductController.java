package com.fsd9.ecom.modules.product.controller;

import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.product.service.EOProductService;
import com.fsd9.ecom.modules.user.model.EOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private EOProductService eoProductService;

    @GetMapping(path = "/categories")
    public List<EOProductCategory> getAllCategory(){
        return this.eoProductService.getAllProdCategory();
    }

    @GetMapping
    public List<EOProduct> getAllProducts(){
        return this.eoProductService.getAllProducts();
    }

    @GetMapping("category/{category}")
    public List<EOProduct> getProductsByCategory(@PathVariable String category) {
        return this.eoProductService.getProductsByCategory(category);
    }
}
