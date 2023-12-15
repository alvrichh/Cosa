package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entidad.PerfilUsuario;
import com.example.demo.entidad.Usuario;
import com.example.demo.servicio.usuario.UsuarioServicio;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UsuarioServicio usuarioServicio;
	 @Autowired
	 private PasswordEncoder passwordEncoder;


    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(Model model, Authentication authentication,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "100") int size,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false) String palabraClave,
                       HttpServletRequest request) {

        String usernameAuth = authentication.getName();
        model.addAttribute("username", usernameAuth);

        Usuario usuario = usuarioServicio.obtenerPorUsername(usernameAuth);
        PerfilUsuario perfilUsuario = usuario.getPerfilUsuario();
        model.addAttribute("perfilUsuario", perfilUsuario);

        // Obtener la lista de usuarios
        Page<Usuario> listaUsuarios = usuarioServicio.obtenerTodosLosUsuarios(page, size);
        model.addAttribute("pageUsuarios", listaUsuarios); // Cambié el nombre a "pageUsuarios" para reflejar que es una página

        return "auth/admin/home";
    }
    // CRUD
    // Método para mostrar el formulario de creación de usuario
    @GetMapping("/crear")
    public String mostrarFormularioDeCrear(Model model) {
    	Usuario usuario = new Usuario();
    	usuario.setPerfilUsuario(new PerfilUsuario());
        model.addAttribute("usuario", new Usuario());
        return "auth/admin/formulario";
    }

    // Método para procesar el formulario de creación de usuario
    @PostMapping("/guardar")
    public String processCreateForm(@ModelAttribute Usuario usuario) {
    	
    	String pass = usuario.getPassword();
    	usuario.setPassword(passwordEncoder.encode(pass));
        usuarioServicio.guardar(usuario);
        
        return "redirect:/admin/home";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "auth/admin/formulario";
    }
    
    // Método para eliminar un usuario
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioServicio.eliminar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Usuario eliminado con éxito");
        return "redirect:/admin/home";
    }
}

