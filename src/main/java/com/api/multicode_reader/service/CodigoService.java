package com.api.multicode_reader.service;

import com.api.multicode_reader.model.Codigo;
import com.api.multicode_reader.repository.CodigoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CodigoService {
    private final CodigoRepository codigoRepository;

    @Transactional
    public void save(Codigo codigo){
        codigo.setFamilia(codigo
                            .getFamilia()
                            .strip());
        codigo.setArticulo(codigo
                            .getArticulo()
                            .strip());

        codigoRepository.save(codigo);
    }

    @Transactional
    public void update(Long id, Codigo datosNuevos) {
        Codigo codigoExistente = findById(id);

        codigoExistente.setFamilia(datosNuevos
                                    .getFamilia()
                                    .strip());
        codigoExistente.setArticulo(datosNuevos
                                    .getArticulo()
                                    .strip());
        codigoExistente.setDescripcion(datosNuevos
                                        .getDescripcion()
                                        .strip());

        codigoRepository.save(codigoExistente);
    }

    @Transactional
    public void delete(Long id){
        codigoRepository.deleteById(id);
    }

    public List<Codigo> findAll() {
        return codigoRepository.findAll();
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


