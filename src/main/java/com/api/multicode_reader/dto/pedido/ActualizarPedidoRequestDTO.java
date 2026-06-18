package com.api.multicode_reader.dto.pedido;

import com.api.multicode_reader.config.TipoPedidoEnum;
import com.api.multicode_reader.dto.pedido.crear_pedido.DetallesPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.EmpleadosPedidoRequestDTO;

import java.util.HashSet;
import java.util.List;

public record ActualizarPedidoRequestDTO(
        String razonSocial,
        TipoPedidoEnum tipoPedido,
        List<EmpleadosPedidoRequestDTO> empleadosAsignados,
        List<DetallesPedidoRequestDTO> pedidosDetalles
) {}
