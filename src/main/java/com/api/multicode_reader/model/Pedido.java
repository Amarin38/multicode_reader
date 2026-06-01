package com.api.multicode_reader.model;

import com.api.multicode_reader.config.EstadoEnum;
import com.api.multicode_reader.config.TipoPedidoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pedido")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEnum estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedidoEnum tipoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> pedidosDetalles = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoAsignado> empleadosAsignados = new ArrayList<>();

    public void agregarEmpleadosAsignados(EmpleadoAsignado empleado) {
        empleadosAsignados.add(empleado);
        empleado.setPedido(this);
    }

    public void agregarDetalle(DetallePedido detalle) {
        pedidosDetalles.add(detalle);
        detalle.setPedido(this);
    }
}
