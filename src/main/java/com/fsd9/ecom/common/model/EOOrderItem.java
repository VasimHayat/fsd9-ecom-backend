package com.fsd9.ecom.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "eoorderitem_sequence", sequenceName ="eoorderitem_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eoorderitem",
        indexes = {
                @Index(name = "idx_eoorderitem_order_product", columnList = "EOOrderID,EOProductID",unique = true),
                @Index(name = "idx_eoorderitem_order", columnList = "EOOrderID",unique = false),
                @Index(name = "idx_eoorderitem_product", columnList = "EOProductID",unique = false)
        }
)
public class EOOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eoorderitem_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EOOrderID",nullable = false)
    private EOOrder eoOrder; // The order to which this item belongs

    @OneToOne
    @JoinColumn(name = "EOProductID",nullable = false)
    private EOProduct eoProduct; // The product associated with this order

    private int quantity; // The quantity of the product in the order
    private double price;
}
