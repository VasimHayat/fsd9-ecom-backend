package com.fsd9.ecom.startup;

import com.fsd9.ecom.modules.product.model.EOProduct;
import com.fsd9.ecom.modules.product.model.EOProductCategory;
import com.fsd9.ecom.modules.product.model.EOProductImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductBean {

    private double price = 0.0;
    private int stock = 0;
    private long sku;
    private String name;
    private String description;
    private String imgUrl;

    private int rating; // The rating given by the reviewer (out of 5)
    String categoryUID; // category uid
    String sellerId; // category uid
    private String keyFeatures; // HTML String -
    private Set<EOProductImg> eoProductImgArray = new LinkedHashSet<>();


    public EOProduct buildEOProduct(EOProductCategory productCategory){
        EOProduct eoProduct = new EOProduct();
        eoProduct.setPrice(this.price);
        eoProduct.setStock(this.stock);
        eoProduct.setSku(this.sku);
        eoProduct.setName(this.name);
        eoProduct.setDescription(this.description);
        eoProduct.setImgUrl(this.imgUrl);
        eoProduct.setRating(this.rating);
        eoProduct.setEoProductCategory(productCategory);
        eoProduct.setKeyFeatures(this.keyFeatures);
        return eoProduct;
    }
}
