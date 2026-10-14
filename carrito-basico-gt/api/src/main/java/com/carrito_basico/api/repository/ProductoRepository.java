package com.carrito_basico.api.repository;

import com.carrito_basico.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// En esta clase de interface Spring Boot hara las consultas a MySQL sin
// necesidad que se tenga que escribir las lines de SQL
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // cuando se ereda de JpaRepository, ya se puede contar con los metodos
    // de findAll(), save(), deleteById().
}
