package com.fsd9.ecom.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "eocart_sequence", sequenceName ="eocart_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eocart",
        indexes = {
                @Index(name = "idx_eocart_user", columnList = "EOUserID",unique = true)
        }
)
public class EOCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eocart_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EOUserID",nullable = false)
    private EOUser eoUser; // The user who owns this cart

    @OneToMany(mappedBy = "eoCart", cascade = CascadeType.ALL)
    private Set<EOCartItem> eoCartItemArray = new LinkedHashSet<>(); // List of items in the cart
}
