package com.skyz.api.core.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "T_APPLICATION_META", schema = "SOUP")
public class ApplicationMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URI")
    private String uri;

    @Column(name = "APPLICATION_TYPE")
    private String type;

    @OneToOne
    @JoinColumn(name = "F_CLIENT_ID", referencedColumnName = "P_ID")
    private SoupClient soupClient;
}
