package com.skyz.api.core.models;

import com.skyz.api.core.models.enums.Scope;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "CLIENT_SECRET")
    private String clientSecret;

    @OneToOne
    @JoinColumn(name = "F_REGISTERED_APPLICATION", referencedColumnName = "P_ID")
    private ApplicationMeta applicationMeta;

    @Column(name = "REGISTERED")
    private boolean registered;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "SCOPE")
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @ManyToOne
    @JoinColumn(name = "F_COUNTRY_CODE", referencedColumnName = "P_ID")
    private SoupClientMandator mandator;
}
