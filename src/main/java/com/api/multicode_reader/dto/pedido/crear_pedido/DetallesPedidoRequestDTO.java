package com.api.multicode_reader.dto.pedido.crear_pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallesPedidoRequestDTO(
    @NotNull(message = "El id del código es obligatorio.")
    @Min(1)
    Long codigoId,

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(1)
    double cantidad
) {}
