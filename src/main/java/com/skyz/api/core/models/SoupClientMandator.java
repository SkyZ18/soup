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
@Table(name = "T_SOUPCLIENT_MANDATOR", schema = "SOUP")
public class SoupClientMandator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;
}
