package com.carrito_basico.api.service;

import com.carrito_basico.api.model.Producto;
import com.carrito_basico.api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List obtenerTodosLosProductos() {
        // Aqui lo que ara es reemplazar al metodo ProductoModel::getAll() de PHP
        return productoRepository.findAll();
    }
}
