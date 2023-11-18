package com.fsd9.ecom.modules.order.model;


import com.fsd9.ecom.modules.product.model.EOProduct;
import jakarta.persistence.*;
import lombok.*;

@Entity
@SequenceGenerator(name = "eocartitem_sequence", sequenceName ="eocartitem_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "eocartitem",
        indexes = {
                @Index(name = "idx_eocartitem_sku", columnList = "eoproductid,eocartid",unique = true)
        }
)
public class EOCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eocartitem_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EOProductID",nullable = false)
    private EOProduct eoProduct; // The product associated with this cart item

    @ManyToOne
    @JoinColumn(name = "EOCartID",nullable = false)
    private EOCart eoCart; // The cart to which this item belongs

    private int quantity; // The quantity of the product in the cart
}
