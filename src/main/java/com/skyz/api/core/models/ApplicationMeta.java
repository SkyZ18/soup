package com.skyz.api.core.models;

import com.skyz.api.core.models.enums.AppTypes;
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
@Table(name = "T_APPLICATION_META", schema = "SOUP")
public class ApplicationMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URI", nullable = false)
    private String uri;

    @Column(name = "APPLICATION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppTypes type;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "F_CLIENT_ID", referencedColumnName = "P_ID")
    private SoupClient soupClient;
}
