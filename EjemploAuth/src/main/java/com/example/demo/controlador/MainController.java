package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // Muestra la página de inicio (index.html)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Muestra la página de inicio de sesión (login.html)
    }
    
    @GetMapping("/home")
    public String home() {
        return "public/home";  
    }
    
    
}