package com.empresa.gestion_empresa.controller;

import com.empresa.gestion_empresa.model.Proveedor;
import com.empresa.gestion_empresa.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Mostrar la lista de proveedores
    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
        return "proveedores/lista";  // Se espera que exista una plantilla Thymeleaf llamada lista.html en la carpeta proveedores
    }

    // Mostrar el formulario para un nuevo proveedor
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/nuevo";  // Se espera que exista una plantilla Thymeleaf llamada nuevo.html en la carpeta proveedores
    }

    // Guardar el proveedor, con validaciones
    @PostMapping("/guardar")
    public String guardarProveedor(@Valid @ModelAttribute Proveedor proveedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "proveedores/nuevo";  // Si hay errores de validación, vuelve al formulario
        }
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/proveedores";
    }

    // Mostrar el formulario para editar un proveedor
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarProveedor(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
            return "proveedores/editar";  // Se espera que exista una plantilla Thymeleaf llamada editar.html en la carpeta proveedores
        } else {
            return "redirect:/proveedores";  // Si el proveedor no se encuentra, redirige a la lista de proveedores
        }
    }

    // Actualizar un proveedor
    @PostMapping("/actualizar/{id}")
    public String actualizarProveedor(@PathVariable Long id, @Valid @ModelAttribute Proveedor proveedor, BindingResult result) {
        if (result.hasErrors()) {
            return "proveedores/editar";  // Si hay errores de validación, vuelve al formulario de edición
        }
        proveedor.setId(id);
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/proveedores";
    }

    // Eliminar un proveedor
    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return "redirect:/proveedores";
    }
}
