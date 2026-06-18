package com.api.multicode_reader.dto.codigo;

import jakarta.validation.constraints.NotBlank;

public record CrearCodigoRequestDTO(
        @NotBlank(message = "El código no puede estar vacío.")
        String codigo,

        String descripcion
) {}
