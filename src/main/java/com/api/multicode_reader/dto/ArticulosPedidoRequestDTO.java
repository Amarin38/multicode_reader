package com.api.multicode_reader.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticulosPedidoRequestDTO {
    @NotNull(message = "El código es obligatorio")
    @Min(1)
    private Long codigoId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(1)
    private double cantidad;
}
