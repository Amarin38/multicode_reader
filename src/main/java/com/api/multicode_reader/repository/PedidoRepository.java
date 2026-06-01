package com.api.multicode_reader.repository;

import com.api.multicode_reader.dto.pedido.crear_pedido.CodigosPedidoResponseDTO;
import com.api.multicode_reader.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Pide los datos a través de un requestDTO
    // Devuelve los datos a través de un responseDTO
    @Query("""
            SELECT new com.api.multicode_reader.dto.crear_pedido.ArticulosPedidoRequestDTO(d.codigo.id, d.cantidad)
            FROM DetallePedido d WHERE d.pedido.id = :pedidoId
           """)
    List<CodigosPedidoResponseDTO> obtenerResumenDetallesPorPedido(@Param("pedidoId") Long pedidoId);
}
