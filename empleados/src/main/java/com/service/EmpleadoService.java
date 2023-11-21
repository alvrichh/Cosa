package com.service;

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
}
