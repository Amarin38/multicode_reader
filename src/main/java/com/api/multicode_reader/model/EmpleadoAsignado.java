package com.api.multicode_reader.model;

import com.api.multicode_reader.model.embedded_keys.EmpleadoAsignadoId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EmpleadoAsignado")
@Getter
@Setter
public class EmpleadoAsignado {
    @EmbeddedId
    private EmpleadoAsignadoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("empleadoId")
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}

