package com.fsd9.ecom.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eorole",
        indexes = {
                @Index(name = "idx_eorole_name", columnList = "name",unique = true)
        }
)
public class EORole {

    //@SequenceGenerator(name = "eorole_sequence", sequenceName ="eorole_sequence", allocationSize = 50)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eorole_sequence")

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "eoRole", cascade = CascadeType.ALL)
    private Set<EOUserRole> eoUserRoleArray = new LinkedHashSet<>();


}
