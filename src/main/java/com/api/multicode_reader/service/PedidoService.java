package com.api.multicode_reader.service;

import com.api.multicode_reader.dto.ArticulosPedidoRequestDTO;
import com.api.multicode_reader.dto.ArticulosPedidoResponseDTO;
import com.api.multicode_reader.model.Codigo;
import com.api.multicode_reader.model.DetallePedido;
import com.api.multicode_reader.model.Pedido;
import com.api.multicode_reader.model.embedded_keys.DetallePedidoId;
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
    private final CodigoService codigoService;

    @Transactional
    public Pedido crearPedidoConDetalles(Pedido nuevoPedido, List<ArticulosPedidoRequestDTO> articulosPedido) {
        for(ArticulosPedidoRequestDTO articulo: articulosPedido) {
            Codigo codigo = codigoService.findById(articulo.getCodigoId());

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(new DetallePedidoId()); // le seteo el id como compuesto
            detallePedido.setCodigo(codigo);
            detallePedido.setCantidad(articulo.getCantidad());

            nuevoPedido.agregarDetalle(detallePedido);
        }
        return pedidoRepository.save(nuevoPedido);
    }

    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                               .orElseThrow(() -> new RuntimeException("Pedido no encontrado."));
    }

    public List<ArticulosPedidoResponseDTO> obtenerDetallePedidoPorId(Long pedidoId) {
        return pedidoRepository.obtenerResumenDetallesPorPedido(pedidoId);
    }

}
