package com.example.demo.controlador;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/home")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "auth/user/home"; // Muestra la página específica del usuario (user.html)
    }

}