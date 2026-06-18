package com.api.multicode_reader.dto.pedido.crear_pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EmpleadosPedidoRequestDTO(
        @NotNull(message = "El id del empleado es obligatorio.")
        @Min(1)
        String nombre
) {}
