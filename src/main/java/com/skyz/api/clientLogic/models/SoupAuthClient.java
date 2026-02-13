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
@Table(name = "T_SOUPAUTHCLIENT", schema = "SOUP")
public class SoupAuthClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "CLIENT_ID", nullable = false)
    private String clientID;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @Column(name = "SCOPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_META", referencedColumnName = "P_ID")
    private ApplicationMeta appMeta;

    @Column(name = "REGISTERED")
    private boolean registered;
}
