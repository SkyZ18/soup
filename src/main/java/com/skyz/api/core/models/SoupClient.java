package com.skyz.api.core.models;

import com.skyz.api.core.models.enums.Scope;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "T_SOUPCLIENT", schema = "SOUP")
public class SoupClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @OneToOne
    @JoinColumn(name = "F_REGISTERED_APPLICATION", referencedColumnName = "P_ID")
    private ApplicationMeta applicationMeta;

    @Column(name = "REGISTERED", nullable = false)
    private boolean registered;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "SCOPE")
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @ManyToOne
    @JoinColumn(name = "F_COUNTRY_CODE", referencedColumnName = "P_ID")
    private SoupClientMandator mandator;
}
