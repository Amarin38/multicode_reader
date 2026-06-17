package com.api.multicode_reader.dto.pedido.crear_pedido;

import com.api.multicode_reader.config.TipoPedidoEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public record CrearPedidoRequestDTO(
        @NotBlank(message = "La razón social no puede estar vacía.")
        String razon_social,

        @NotNull(message = "La fecha no puede estar vacía.")
        @FutureOrPresent
        LocalDate fecha_pedido,

        @NotNull(message = "El tipo de pedido no puede estar vacío.")
        TipoPedidoEnum tipo_pedido,

        @NotEmpty(message = "La lista de empleados no puede estar vacía.")
        @Valid
        HashSet<EmpleadosPedidoRequestDTO> empleadosAsignados,

        @NotEmpty(message = "La lista de artículos no puede estar vacía.")
        @Valid
        List<DetallesPedidoRequestDTO> articulosPedidos
) {}
