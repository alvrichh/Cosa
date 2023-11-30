package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.servicio.ComentarioServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	 @Autowired
	 private ComentarioServicio comentarioServicio;


    @GetMapping("/")
    public String index() {
        return "index"; // Muestra la página de inicio (index.html)
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("SPRING_SECURITY_CONTEXT") != null);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "login"; // Nombre de tu plantilla de inicio de sesión
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("comentarios", comentarioServicio.obtenerTodosLosComentarios());
        return "public/home"; // Muestra la página home con los comentarios
    }    
    
}