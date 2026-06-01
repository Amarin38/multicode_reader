package com.api.multicode_reader.service;

import com.api.multicode_reader.config.EstadoEnum;
import com.api.multicode_reader.dto.pedido.crear_pedido.CodigosPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.CodigosPedidoResponseDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.CrearPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.EmpleadosPedidoRequestDTO;
import com.api.multicode_reader.model.*;
import com.api.multicode_reader.model.embedded_keys.DetallePedidoId;
import com.api.multicode_reader.model.embedded_keys.EmpleadoAsignadoId;
import com.api.multicode_reader.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CodigoService codigoService;
    private final EmpleadoService empleadoService;

    @Transactional
    public Pedido crearNuevoPedido(CrearPedidoRequestDTO dto) {
        Pedido nuevoPedido = new Pedido();

        nuevoPedido.setRazonSocial(dto.razon_social());
        nuevoPedido.setFechaPedido(dto.fecha_pedido());
        nuevoPedido.setTipoPedido(dto.tipo_pedido());
        nuevoPedido.setEstado(EstadoEnum.PENDIENTE);

        // Agrego empleados asignados al pedido
        for(EmpleadosPedidoRequestDTO empleados: dto.empleadosAsignados()) {
            Empleado empleado = empleadoService.findById(empleados.empleadoId());
            EmpleadoAsignado empleadoAsignado = new EmpleadoAsignado();

            empleadoAsignado.setId(new EmpleadoAsignadoId()); // le seteo el id como compuesto
            empleadoAsignado.setEmpleado(empleado);

            nuevoPedido.agregarEmpleadosAsignados(empleadoAsignado);
        }

        // Agregp detalles al pedido
        for(CodigosPedidoRequestDTO articulo: dto.articulosPedidos()) {
            Codigo codigo = codigoService.findById(articulo.codigoId());

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(new DetallePedidoId()); // le seteo el id como compuesto
            detallePedido.setCodigo(codigo);
            detallePedido.setCantidad(articulo.cantidad());

            nuevoPedido.agregarDetalle(detallePedido);
        }

        return pedidoRepository.save(nuevoPedido);
    }

    @Transactional
    public Pedido actualizarPedido()

    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                               .orElseThrow(() -> new RuntimeException("Pedido no encontrado."));
    }

    public List<CodigosPedidoResponseDTO> obtenerDetallePedidoPorId(Long pedidoId) {
        return pedidoRepository.obtenerResumenDetallesPorPedido(pedidoId);
    }
}
