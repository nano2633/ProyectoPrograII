package com.carrito_basico.api.controller;

import com.carrito_basico.api.model.Producto;
import com.carrito_basico.api.service.ProductoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping
    public List listarProductos(){
        return productoService.obtenerTodosLosProductos();
    }
}
