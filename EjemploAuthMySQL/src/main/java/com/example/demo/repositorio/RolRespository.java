package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Rol;
import com.example.demo.entidad.enumerado.RolEnum;

@Repository
public interface RolRespository extends JpaRepository<Rol, Long>  {
	Optional<Rol> findByRol(RolEnum rol);
}
