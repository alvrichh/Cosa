package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CuentaUsuario;
import com.model.Empleado;
import com.model.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	public Empleado crearEmpleadoConCuentaUsuario(Empleado empleado, CuentaUsuario cuentaUsuario) {
		empleado.setCuentaUsuario(cuentaUsuario);
		cuentaUsuario.setEmpleado(empleado);
		return empleadoRepository.save(empleado);
	}

	public List<Empleado> listarTodos() {

		return empleadoRepository.findAll();
	}

	public Empleado guardarEmpleado(Empleado empleado) {

		return empleadoRepository.save(empleado);
	}

	public void eliminarCuenta(Long id) {
		empleadoRepository.deleteById(id);
	}
	public Empleado obtenerPorId(Long id) {
		return empleadoRepository.findById(id).orElseThrow(() ->
		new IllegalArgumentException("Empleado no encontrado con id: "+ id));
	}
}