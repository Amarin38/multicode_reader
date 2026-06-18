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
    private Long codigoId;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "decrcipcion")
    private String descripcion;
}
