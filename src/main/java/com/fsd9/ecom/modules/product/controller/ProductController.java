package com.fsd9.ecom.modules.product.controller;

import com.fsd9.ecom.modules.product.dto.ProductReqDto;
import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.repositories.EOProductCategoryRepository;
import com.fsd9.ecom.modules.product.service.EOProductService;
import com.fsd9.ecom.modules.user.model.EOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("seller/{sellerId}")
    public List<EOProduct> getProductsBySeller(@PathVariable String sellerId) {
        return this.eoProductService.findBySellerId(Long.valueOf(sellerId));
    }

    @GetMapping("/{productId}")
    public EOProduct getProductById(@PathVariable String productId) {
        return this.eoProductService.findById(Long.valueOf(productId));
    }

    @PutMapping("/{productId}")
    public EOProduct updateProductById(@PathVariable ProductReqDto reqDto) {
        return null;
    }


    @DeleteMapping("/{productId}")
    public EOProduct deleteProduct(@PathVariable String id) {
        return null; //this.eoProductService.findById(Long.valueOf(id));
    }


}
