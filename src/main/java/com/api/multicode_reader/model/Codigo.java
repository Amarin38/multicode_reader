package com.api.multicode_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "codigo")
@Getter
@Setter
public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familia;
    private String articulo;
    private int cantidad;

    private List<Pedido>
}
