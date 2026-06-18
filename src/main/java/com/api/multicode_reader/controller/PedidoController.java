package com.api.multicode_reader.controller;

import com.api.multicode_reader.dto.pedido.crear_pedido.DetallesPedidoRequestDTO;
import com.api.multicode_reader.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedido")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarCodigos(@RequestBody List<DetallesPedidoRequestDTO> detalles) {
        System.out.println("--- Se recibieron " + detalles.size() + " códigos ---");

        for (DetallesPedidoRequestDTO codigo : detalles) {
            System.out.println("Código: " + codigo.codigo() + "("+codigo.cantidad()+")");
        }

        return ResponseEntity.ok("¡Datos guardados con éxito en la base de datos!");
    }

}
