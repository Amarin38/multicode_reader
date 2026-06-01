package com.api.multicode_reader.service;

import com.api.multicode_reader.dto.empleado.ActualizarEmpleadoRequestDTO;
import com.api.multicode_reader.dto.empleado.CrearEmpleadoRequestDTO;
import com.api.multicode_reader.mapper.EmpleadoMapper;
import com.api.multicode_reader.model.Empleado;
import com.api.multicode_reader.repository.EmpleadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Transactional
    public void crearEmpleado(CrearEmpleadoRequestDTO dto){
        Empleado empleado = EmpleadoMapper.crearToEntity(dto);
        empleadoRepository.save(empleado);
    }

    @Transactional
    public Empleado actualizarEmpleado(Long id, ActualizarEmpleadoRequestDTO dto){
        Empleado empleadoExistente = findById(id);

        Optional.ofNullable(dto.nombre())
                .filter(nom -> !nom.isBlank())
                .ifPresent(empleadoExistente::setNombre);

        Optional.ofNullable(dto.apellido())
                .filter(ape -> !ape.isBlank())
                .ifPresent(empleadoExistente::setApellido);

        return empleadoExistente;
    }

    public Empleado findById(Long id){
        return empleadoRepository.findById(id)
                                 .orElseThrow(() -> new RuntimeException("No existe el empleado."));
    }
}
