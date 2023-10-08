package com.fsd9.ecom.modules.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "eoproductimg_sequence", sequenceName ="eoproductimg_sequence", allocationSize = 50)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "eoproductimg")
public class EOProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eoproductimg_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EOProductID")
    @JsonIgnore
    EOProduct eoProduct;
    
    private String imgUrl;
}
