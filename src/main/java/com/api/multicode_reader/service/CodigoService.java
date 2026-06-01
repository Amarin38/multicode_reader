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
    public Codigo actualizarCodigo(Long id, ActualizarCodigoRequestDTO dto) {
        Codigo codigoExistente = findById(id);

        Optional.ofNullable(dto.familia())
                .filter(fam -> !fam.isBlank())
                .ifPresent(codigoExistente::setFamilia);

        Optional.ofNullable(dto.articulo())
                .filter(art -> !art.isBlank())
                .ifPresent(codigoExistente::setArticulo);

        Optional.ofNullable(dto.descripcion())
                .filter(desc -> !desc.isBlank())
                .ifPresent(codigoExistente::setDescripcion);

        return codigoExistente;
    }

    public Codigo findById(long id) {
        return codigoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Código no encontrado"));
    }

    public Codigo findByCodigo(String codigo) {
        String[] codigo_separado = codigo.split("\\.");
        String familia = codigo_separado[0];
        String articulo =  codigo_separado[1];

        return codigoRepository.findByFamiliaLikeAndArticuloLike(familia.strip(), articulo.strip());
    }

    public Codigo findByFamilia(String familia) {
        return codigoRepository.findByFamiliaLike(familia.strip());
    }
}


