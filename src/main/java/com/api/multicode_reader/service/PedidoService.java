package com.api.multicode_reader.service;

import com.api.multicode_reader.dto.request_dto.AritculosPedidoDTO;
import com.api.multicode_reader.model.Codigo;
import com.api.multicode_reader.model.DetallePedido;
import com.api.multicode_reader.model.Pedido;
import com.api.multicode_reader.model.embedded_keys.DetallePedidoId;
import com.api.multicode_reader.repository.CodigoRepository;
import com.api.multicode_reader.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CodigoRepository codigoRepository;

    @Transactional
    public Pedido crearPedidoConDetalles(Pedido nuevoPedido, List<AritculosPedidoDTO> articulosPedido) {
        for(AritculosPedidoDTO articulo: articulosPedido) {
            Codigo codigo = codigoRepository.findById(articulo.getCodigoId())
                                            .orElseThrow(() -> new RuntimeException("Codigo no encontrado."));

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(new DetallePedidoId()); // le seteo el id como compuesto
            detallePedido.setCodigo(codigo);
            detallePedido.setCantidad(articulo.getCantidad());

            nuevoPedido.agregarDetalle(detallePedido);
        }
        return pedidoRepository.save(nuevoPedido);
    }


    @Transactional
    public void agregarArticuloAPedido(Long pedidoId, Long codigoId) {

    }
}
