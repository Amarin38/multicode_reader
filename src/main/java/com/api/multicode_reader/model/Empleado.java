package com.api.multicode_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empleado")
@Getter
@Setter
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @OneToMany(mappedBy = "empleado")
    private List<EmpleadoAsignado> empleadosAsignados = new ArrayList<>();

    public void agregarEmpleadosAsignados(EmpleadoAsignado empleado) {
        empleadosAsignados.add(empleado);
        empleado.setEmpleado(this);
    }
}
