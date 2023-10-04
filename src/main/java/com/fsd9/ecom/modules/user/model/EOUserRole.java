package com.fsd9.ecom.modules.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity(name = "eouserrole")
@Getter
@Setter
@SequenceGenerator(name = "eouserrole_sequence", sequenceName ="eouserrole_sequence", allocationSize = 50)

public class EOUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eouserrole_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EORoleID", nullable = false)
    private EORole eoRole;

    @ManyToOne
    @JoinColumn(name = "EOUserID", nullable = false)
    private EOUser eoUser;



}
