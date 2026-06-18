package com.api.multicode_reader.repository;

import com.api.multicode_reader.model.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
    Codigo findByCodigoLike (String codigo);
}
