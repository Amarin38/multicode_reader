package com.api.multicode_reader.service;

import com.api.multicode_reader.config.EstadoEnum;
import com.api.multicode_reader.dto.pedido.ActualizarPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.DetallesPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.DetallesPedidoResponseDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.CrearPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.EmpleadosPedidoRequestDTO;
import com.api.multicode_reader.model.*;
import com.api.multicode_reader.model.embedded_keys.DetallePedidoId;
import com.api.multicode_reader.model.embedded_keys.EmpleadoAsignadoId;
import com.api.multicode_reader.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

        nuevoPedido.setRazonSocial(dto.razonSocial());
        nuevoPedido.setFechaPedido(dto.fecha());
        nuevoPedido.setEstado(EstadoEnum.PENDIENTE);

//        agregarEmpleados(nuevoPedido, dto.empleadosAsignados());
        agregarDetalles(nuevoPedido, dto.codigos());

        return pedidoRepository.save(nuevoPedido);
    }

    @Transactional
    public Pedido actualizarPedido(Long id, ActualizarPedidoRequestDTO dto){
        Pedido pedidoExistente = findById(id);

        Optional.ofNullable(dto.tipoPedido())
                .ifPresent(pedidoExistente::setTipoPedido);

        Optional.ofNullable(dto.razonSocial())
                .filter(razon -> !razon.isBlank())
                .ifPresent(pedidoExistente::setRazonSocial);

        Optional.ofNullable(dto.pedidosDetalles())
                .filter(listaArt -> !listaArt.isEmpty())
                .ifPresent(detalles -> agregarDetalles(pedidoExistente, detalles));

        Optional.ofNullable(dto.empleadosAsignados())
                .filter(listaEmple -> !listaEmple.isEmpty())
                .ifPresent(empleados -> agregarEmpleados(pedidoExistente, empleados));

        return pedidoExistente;
    }

    private void agregarEmpleados(Pedido pedido, List<EmpleadosPedidoRequestDTO> empleadosDTO) {
        for(EmpleadosPedidoRequestDTO dto: empleadosDTO) {


            boolean exists = pedido.getEmpleadosAsignados()
                    .stream()
                    .anyMatch(empleAsign -> empleAsign.getEmpleado()
                                                                        .getNombre()
                                                                        .equals(dto.nombre())
                    );

            if(!exists) {
                List<Empleado> empleados = empleadoService.findByNombre(dto.nombre());
                EmpleadoAsignado empleadoAsignado = new EmpleadoAsignado();

                empleadoAsignado.setId(new EmpleadoAsignadoId()); // le seteo el id como compuesto
                empleadoAsignado.setEmpleado(empleados.getFirst());

                pedido.agregarEmpleadosAsignados(empleadoAsignado);
            }
        }
    }

    private void agregarDetalles(Pedido pedido, List<DetallesPedidoRequestDTO> codigosDTO) {
        for (DetallesPedidoRequestDTO dto : codigosDTO) {
            Codigo codigo = codigoService.findByCodigo(dto.codigo());

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(new DetallePedidoId()); // le seteo el id como compuesto
            detallePedido.setCodigo(codigo);
            detallePedido.setCantidad(dto.cantidad());

            pedido.agregarDetalle(detallePedido);
        }
    }

    public Pedido findById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                               .orElseThrow(() -> new RuntimeException("Pedido no encontrado."));
    }

    public List<DetallesPedidoResponseDTO> obtenerDetallePedidoPorId(Long pedidoId) {
        return pedidoRepository.obtenerResumenDetallesPorPedido(pedidoId);
    }
}
