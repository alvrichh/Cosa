package com.example.demo.controlador;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/home")
    @PreAuthorize("hasRole('MANAGER')")
    public String user() {
        return "auth/manager/home"; // Muestra la página específica del usuario (admin.html)
    }

}