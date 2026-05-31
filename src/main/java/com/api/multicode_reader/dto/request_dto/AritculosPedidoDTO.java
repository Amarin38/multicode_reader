package com.api.multicode_reader.dto.request_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AritculosPedidoDTO {
    @NotNull(message = "El código es obligatorio")
    @Min(1)
    private Long codigoId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(1)
    private double cantidad;
}
