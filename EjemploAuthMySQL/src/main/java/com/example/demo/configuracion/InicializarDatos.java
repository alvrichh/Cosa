package com.example.demo.configuracion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entidad.Comentario;
import com.example.demo.entidad.Rol;
import com.example.demo.entidad.Usuario;
import com.example.demo.entidad.enumerado.RolEnum;
import com.example.demo.repositorio.ComentarioRepository;
import com.example.demo.repositorio.RolRespository;
import com.example.demo.repositorio.UsuarioRepository;
import com.github.javafaker.Faker;

@Component
public class InicializarDatos implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private RolRespository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    Faker faker = new Faker();
    @Override
    public void run(String... args) throws Exception {
        // Asegúrate de que los roles existan
        Rol rolUsuario = crearOBuscarRol(RolEnum.ROLE_USER);
        System.out.println("id rolUsuario: " + rolUsuario.getId());
        
        Rol rolAdmin = crearOBuscarRol(RolEnum.ROLE_ADMIN);
        System.out.println("id rolAdmin: " + rolAdmin.getId());
        
        // Crear o buscar el usuario 'user1' y asignarle el rol 'ROLE_USER'
        crearOBuscarUsuario("user1", "user1", rolUsuario);
        crearOBuscarUsuario("user2", "user2", rolUsuario);
        crearOBuscarUsuario("admin", "admin", rolAdmin);
        // Crear comentarios
        crearComentarioUsuario("user1");
        crearComentarioUsuario("user2");
    }

    private Rol crearOBuscarRol(RolEnum rolEnum) {
        return rolRepository.findByRol(rolEnum).orElseGet(() -> {
            Rol nuevoRol = new Rol();
            nuevoRol.setRol(rolEnum);
            return rolRepository.save(nuevoRol);
        });
    }

    private Usuario crearOBuscarUsuario(String username, String password, Rol rol) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseGet(() -> {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setPassword(passwordEncoder.encode(password));
            nuevoUsuario.getRoles().add(rol);
            return usuarioRepository.save(nuevoUsuario);
        });
        return usuario;
    }
    
    private void crearComentarioUsuario(String usuario) {
    	Usuario user = usuarioRepository.findByUsername(usuario).orElse(null);
    	for (int i = 0; i < 5; i++) {
            Comentario comentario = new Comentario();
            comentario.setContenido(faker.lorem().paragraph());
            comentario.setUsuario(user);
            comentario.setFechaCreacion(new Date());
            comentarioRepository.save(comentario);
        }
    }
}