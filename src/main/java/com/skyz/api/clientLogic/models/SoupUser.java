package com.skyz.api.clientLogic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyz.api.clientLogic.models.enums.Scope;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "T_SOUPUSER", schema = "SOUP")
public class SoupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "SCOPE")
    @Enumerated(EnumType.STRING)
    private Scope scope;
}
