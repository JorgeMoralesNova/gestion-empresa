package com.empresa.gestion_empresa.controller;

import com.empresa.gestion_empresa.model.Producto;
import com.empresa.gestion_empresa.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Mostrar la lista de productos
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "productos/lista";  // Se espera que exista una plantilla Thymeleaf llamada lista.html en la carpeta productos
    }

    // Mostrar el formulario para un nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/nuevo";  // Se espera que exista una plantilla Thymeleaf llamada nuevo.html en la carpeta productos
    }

    // Guardar el producto, con validaciones
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "productos/nuevo";  // Si hay errores de validación, vuelve al formulario
        }
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    // Mostrar el formulario para editar un producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "productos/editar";  // Se espera que exista una plantilla Thymeleaf llamada editar.html en la carpeta productos
        } else {
            return "redirect:/productos";  // Si el producto no se encuentra, redirige a la lista de productos
        }
    }

    // Actualizar un producto
    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @Valid @ModelAttribute Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return "productos/editar";  // Si hay errores de validación, vuelve al formulario de edición
        }
        producto.setId(id);
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    // Eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
