package com.api.multicode_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="pedido")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp fecha_pedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_id")
    private Codigo codigo;

}
