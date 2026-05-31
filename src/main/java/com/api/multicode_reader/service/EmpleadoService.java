package com.api.multicode_reader.service;

import com.api.multicode_reader.repository.CodigoRepository;
import com.api.multicode_reader.repository.EmpleadoRepository;
import com.api.multicode_reader.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class EmpleadoService {
    private final PedidoRepository pedidoRepository;
    private final EmpleadoRepository empleadoRepository;


}
