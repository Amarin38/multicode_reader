package com.api.multicode_reader.dto.codigo;

import jakarta.validation.constraints.NotBlank;

public record CrearCodigoRequestDTO(
        @NotBlank(message = "La familia no puede estar vacía.")
        String familia,

        @NotBlank(message = "El artículo no puede estar vacío.")
        String articulo,

        String descripcion
) {}
