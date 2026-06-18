package com.api.multicode_reader.dto.pedido.crear_pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public record DetallesPedidoRequestDTO(
    @NotNull(message = "El código es obligatorio.")
    String codigo,

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(1)
    double cantidad
) {}
