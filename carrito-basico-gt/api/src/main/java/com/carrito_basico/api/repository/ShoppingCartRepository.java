package com.carrito_basico.api.repository;

import com.carrito_basico.api.model.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    // aqui se reemplaza el SELECT hasta WERE codUsuario like '$cod_usr
    List<ShoppingCart> findByCodUsuario(String codUsuario);

    // sirve para identificar si un producto ya esta en el carrito de un usuario
    ShoppingCart findByCodUsuarioAndIdProducto(String codUsuario, Long idProducto);

    // ahora la siguiente linea serve para reeplazar la funcion eliminarTodos($cod_usr) de php
    @Transactional
    void deleteByCodUsuario(String codUsuario);
}
