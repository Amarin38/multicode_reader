package com.api.multicode_reader.mapper;

import com.api.multicode_reader.dto.codigo.CrearCodigoRequestDTO;
import com.api.multicode_reader.model.Codigo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CodigoMapper {
    public static Codigo crearToEntity(CrearCodigoRequestDTO dto){
        Codigo codigo = new Codigo();

        codigo.setCodigo(dto.codigo().strip());
        codigo.setDescripcion(dto.descripcion().strip());

        return codigo;
    }
}
