package com.api.multicode_reader.service;

import com.api.multicode_reader.dto.codigo.ActualizarCodigoRequestDTO;
import com.api.multicode_reader.dto.codigo.CrearCodigoRequestDTO;
import com.api.multicode_reader.mapper.CodigoMapper;
import com.api.multicode_reader.model.Codigo;
import com.api.multicode_reader.repository.CodigoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CodigoService {
    private final CodigoRepository codigoRepository;

    @Transactional
    public void crearCodigo(CrearCodigoRequestDTO dto){
        Codigo codigo = CodigoMapper.crearToEntity(dto);
        codigoRepository.save(codigo);
    }

    @Transactional
    public Codigo actualizarCodigo(String id, ActualizarCodigoRequestDTO dto) {
        Codigo codigoExistente = findByCodigo(id);

        Optional.ofNullable(dto.articulo())
                .filter(cod -> !cod.isBlank())
                .ifPresent(codigoExistente::setCodigo);

        Optional.ofNullable(dto.descripcion())
                .filter(desc -> !desc.isBlank())
                .ifPresent(codigoExistente::setDescripcion);

        return codigoExistente;
    }

    public Codigo findByCodigo(String codigo) {
        String[] codigo_separado = codigo.split("\\.");
        String familia = codigo_separado[0];
        String articulo =  codigo_separado[1];

        return codigoRepository.findByCodigoLike(codigo);
    }
}


