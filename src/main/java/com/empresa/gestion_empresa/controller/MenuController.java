package com.empresa.gestion_empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String mostrarMenu() {
        return "menu"; // Devuelve la vista menu.html
    }
}