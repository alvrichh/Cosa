package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Comentario;
import com.example.demo.repositorio.ComentarioRepository;

@Service
public class ComentarioServicio {
	
	@Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll();
    }

}