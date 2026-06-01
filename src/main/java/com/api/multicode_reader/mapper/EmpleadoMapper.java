package com.api.multicode_reader.mapper;

import com.api.multicode_reader.dto.empleado.CrearEmpleadoRequestDTO;
import com.api.multicode_reader.model.Empleado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmpleadoMapper {
    public static Empleado crearToEntity(CrearEmpleadoRequestDTO dto){
        Empleado empleado = new Empleado();

        empleado.setNombre(dto.nombre().strip());
        empleado.setApellido(dto.apellido().strip());

        return empleado;
    }
}
