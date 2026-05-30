package com.api.multicode_reader.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoAsignado> asignados = new ArrayList<>();


    public void agregarEmpleadosAsignados(EmpleadoAsignado asignado) {
        asignados.add(asignado);
        asignado.setEmpleado(this);
    }
}
