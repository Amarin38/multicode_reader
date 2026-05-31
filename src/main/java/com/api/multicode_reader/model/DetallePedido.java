package com.api.multicode_reader.model;

import com.api.multicode_reader.model.embedded_keys.DetallePedidoId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DetallePedido")
@Getter
@Setter
public class DetallePedido {
    @EmbeddedId
    private DetallePedidoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("codigoId")
    @JoinColumn(name = "codigo_id")
    private Codigo codigo;

    @Column(name = "cantidad", nullable = false)
    private double cantidad;
}
