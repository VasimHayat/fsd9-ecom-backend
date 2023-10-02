package com.fsd9.ecom.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "eoorder_sequence", sequenceName ="eoorder_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eoorder",
        indexes = {
                @Index(name = "idx_eoorder_user", columnList = "EOUserID",unique = false)
        }
)
public class EOOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eoorder_sequence")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "EOUserID")
    private EOUser eoUser; // The user who placed the order

    @OneToMany(mappedBy = "eoOrder")
    private Set<EOOrderItem> eoOrderItemArray = new LinkedHashSet<>(); // List of items in the order


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long uuid;

    private String address; // Shipping address for the order

    private String phoneNumber; // Contact number for the order

    private double totalAmount; // Total amount of the order

    private Timestamp orderDate; // Date and time when the order was placed
}
