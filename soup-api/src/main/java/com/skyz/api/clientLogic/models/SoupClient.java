package com.skyz.api.clientLogic.models;

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
@Table(name = "T_SOUPCLIENT", schema = "SOUP")
public class SoupClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "SCOPE")
    private Scope scope;

    @OneToOne
    @JoinColumn(name = "F_CLIENTID", referencedColumnName = "P_ID", nullable = false)
    private SoupClientID clientID;
}
