package com.api.multicode_reader.controller;

import com.api.multicode_reader.dto.pedido.crear_pedido.CrearPedidoRequestDTO;
import com.api.multicode_reader.dto.pedido.crear_pedido.DetallesPedidoRequestDTO;
import com.api.multicode_reader.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedido")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarCodigos(@RequestBody CrearPedidoRequestDTO pedido) {
        System.out.println("JSON:" + pedido);
        System.out.println("Razón Social: " + pedido.razonSocial());
        System.out.println("Fecha pedido: " + pedido.fecha());

        for (String empleado : pedido.empleadosAsignados()) {
            System.out.println("Empleado: " + empleado);
        }

        for (DetallesPedidoRequestDTO codigo : pedido.codigos()) {
            System.out.println("Código: " + codigo.codigo() + "("+codigo.cantidad()+")");
        }

        return ResponseEntity.ok("¡Datos guardados con éxito en la base de datos!");
    }

}
