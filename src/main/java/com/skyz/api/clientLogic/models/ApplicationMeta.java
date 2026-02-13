package com.skyz.api.clientLogic.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "T_APPLICATIONMETA", schema = "SOUP")
public class ApplicationMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(name = "APP_IDENTIFIER")
    private String identity;

    @Column(name = "APPLICATION_NAME")
    private String appName;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "CREATION_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate;
}
