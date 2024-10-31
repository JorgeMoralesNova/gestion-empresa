package com.empresa.gestion_empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Muestra la página principal de la aplicación
    @GetMapping("/home")
    public String mostrarPaginaPrincipal(Model model) {
        return "home"; // Renderiza la vista "home.html"
    }

    // Muestra la vista de selección de Inventario
    @GetMapping("/inventario")
    public String mostrarInventario() {
        return "inventario/inventario"; // Renderiza la vista "inventario.html"
    }
}
