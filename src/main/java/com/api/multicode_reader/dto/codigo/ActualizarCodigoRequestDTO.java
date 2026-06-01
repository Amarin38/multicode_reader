package com.api.multicode_reader.dto.codigo;

public record ActualizarCodigoRequestDTO(
        String familia,
        String articulo,
        String descripcion
) {}
