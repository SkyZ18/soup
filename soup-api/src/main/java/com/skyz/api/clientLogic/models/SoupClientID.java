package com.skyz.api.clientLogic.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "T_SOUPCLIENTID", schema = "SOUP")
public class SoupClientID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "CLIENTID")
    private String clientID;

    @OneToOne
    @JoinColumn(name = "F_CLIENT", referencedColumnName = "P_ID")
    private SoupClient soupClient;
}
