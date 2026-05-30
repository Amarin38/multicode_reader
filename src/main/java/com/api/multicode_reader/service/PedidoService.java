package com.api.multicode_reader.service;

import com.api.multicode_reader.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CodigoService codigoService;


}
