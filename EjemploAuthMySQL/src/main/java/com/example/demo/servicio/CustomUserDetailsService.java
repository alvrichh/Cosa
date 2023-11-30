package com.example.demo.servicio;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Usuario;
import com.example.demo.repositorio.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario usuario = usuarioRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

	       return new User(usuario.getUsername(), usuario.getPassword(), 
	                usuario.getRoles().stream()
	                        .map(rol -> new SimpleGrantedAuthority(rol.getRol().toString()))
	                        .collect(Collectors.toList()));
	}


}
