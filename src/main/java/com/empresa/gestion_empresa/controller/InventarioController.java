package com.empresa.gestion_empresa.controller;

import com.empresa.gestion_empresa.model.Producto;
import com.empresa.gestion_empresa.model.Proveedor;
import com.empresa.gestion_empresa.service.ProductoService;
import com.empresa.gestion_empresa.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    // ====================== PRODUCTOS ======================



    @GetMapping("/productos")
    public String listarProductos(
            @RequestParam(defaultValue = "") String terminoBusqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<Producto> paginaDeProductos = productoService.obtenerProductosPaginados(terminoBusqueda, page, size);

        model.addAttribute("productos", paginaDeProductos.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginaDeProductos.getTotalPages());
        model.addAttribute("terminoBusqueda", terminoBusqueda); // Añade el término de búsqueda al modelo para la vista

        return "inventario/productos/lista";

    }

    // Endpoint para sugerencias de productos
    @GetMapping("/productos/sugerencias")
    @ResponseBody
    public List<String> obtenerSugerencias(@RequestParam("term") String term) {
        return productoService.obtenerSugerenciasPorNombre(term);
    }


    @GetMapping("/productos/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "inventario/productos/nuevo";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inventario/productos/nuevo";
        }
        productoService.guardarProducto(producto);
        return "redirect:/inventario/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioDeEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "inventario/productos/editar";
        } else {
            return "redirect:/inventario/productos";
        }
    }

    @PostMapping("/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @Valid @ModelAttribute Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return "inventario/productos/editar";
        }
        producto.setId(id);
        productoService.guardarProducto(producto);
        return "redirect:/inventario/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/inventario/productos";
    }

    // ====================== PROVEEDORES ======================

    @GetMapping("/proveedores")
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
        return "inventario/proveedores/lista";
    }

    @GetMapping("/proveedores/nuevo")
    public String mostrarFormularioDeNuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "inventario/proveedores/nuevo";
    }

    @PostMapping("/proveedores/guardar")
    public String guardarProveedor(@Valid @ModelAttribute Proveedor proveedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inventario/proveedores/nuevo";
        }
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/inventario/proveedores";
    }

    @GetMapping("/proveedores/editar/{id}")
    public String mostrarFormularioDeEditarProveedor(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
            return "inventario/proveedores/editar";
        } else {
            return "redirect:/inventario/proveedores";
        }
    }

    @PostMapping("/proveedores/actualizar/{id}")
    public String actualizarProveedor(@PathVariable Long id, @Valid @ModelAttribute Proveedor proveedor, BindingResult result) {
        if (result.hasErrors()) {
            return "inventario/proveedores/editar";
        }
        proveedor.setId(id);
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/inventario/proveedores";
    }

    @GetMapping("/proveedores/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return "redirect:/inventario/proveedores";
    }
}
