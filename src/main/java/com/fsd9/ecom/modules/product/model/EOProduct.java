package com.fsd9.ecom.modules.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fsd9.ecom.modules.user.model.EOUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "eoproduct_sequence", sequenceName ="eoproduct_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eoproduct",
        indexes = {
                @Index(name = "idx_eoproduct_sku", columnList = "sku",unique = true),
                @Index(name = "idx_eoproduct_name", columnList = "name",unique = false),
                @Index(name = "idx_eoproduct_category", columnList = "EOProductCategoryID",unique = false),
                @Index(name = "idx_eoproduct_sku_name", columnList = "name,sku",unique = false)
        }
)
public class EOProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eoproduct_sequence")
    private Long id;

    private double price = 0.0;
    private int stock = 0;

    @Column(unique = true,nullable = false)
    private long sku;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String imgUrl;

    private int rating; // The rating given by the reviewer (out of 5)

    @ManyToOne
    @JoinColumn(name = "EOProductCategoryID")
    EOProductCategory eoProductCategory;

    @Column(columnDefinition = "TEXT")
    private String keyFeatures; // HTML String -

    @OneToMany(mappedBy = "eoProduct",cascade = CascadeType.ALL)
    private Set<EOProductImg> eoProductImgArray = new LinkedHashSet<>();


    @ManyToOne
    @JoinColumn(name = "SellerId",nullable = false)
    private EOUser sellerUser;


}
