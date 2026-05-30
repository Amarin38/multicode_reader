package com.api.multicode_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Codigo")
@Getter
@Setter
public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "familia", nullable = false)
    private String familia;

    @Column(name = "articulo", nullable = false)
    private String articulo;

    @Column(name = "decrcipcion", nullable = true)
    private String descripcion;
}
