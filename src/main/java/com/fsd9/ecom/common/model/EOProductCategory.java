package com.fsd9.ecom.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@SequenceGenerator(name = "eoproductcategory_sequence", sequenceName ="idx_eoproductcategory_sku", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eoproductcategory",
        indexes = {
                @Index(name = "idx_eoproductcategory_sku", columnList = "name",unique = true),
                @Index(name = "idx_eoproductcategory_name", columnList = "description",unique = false)
        }
)
public class EOProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idx_eoproductcategory_sku")
    private Long id;

    @Column(unique = true)
    private String name; // Name of the category

    @Column(columnDefinition = "TEXT")
    private String description; // Description of the category

    @OneToMany(mappedBy = "eoProductCategory",cascade = CascadeType.ALL)
    private Set<EOProduct> eoProductArray = new LinkedHashSet<>();

}
