package com.api.multicode_reader.dto.empleado;

import jakarta.validation.constraints.NotBlank;

public record CrearEmpleadoRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío.")
        String apellido
) {}
