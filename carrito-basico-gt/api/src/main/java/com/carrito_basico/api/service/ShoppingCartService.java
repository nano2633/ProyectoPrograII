package com.carrito_basico.api.service;

import com.carrito_basico.api.model.Producto;
import com.carrito_basico.api.model.ShoppingCart;
import com.carrito_basico.api.repository.ProductoRepository;
import com.carrito_basico.api.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

// Se usara la estructura de datos Map de java para construir un JSON a medida que incluya
// los datos del producto y el calculo matematico del subtotal, imitando el resultado del
// SELECT con INNER JOIN que esta en el proyecto en PHP.
@Service
public class ShoppingCartService {
    private final ShoppingCartRepository cartRepository;
    private final ProductoRepository productoRepository;

    // Ahora se introduciran ambos repositorios para poder cruzar los datos
    public ShoppingCartService(ShoppingCartRepository cartRepository, ProductoRepository productoRepository) {
        this.cartRepository = cartRepository;
        this.productoRepository = productoRepository;
    }

    // Ahora se obtendra el carrito, aqui se reemplazara el metodo getAll que esta en PHP
    public List<Map<String, Object>> obtenerCarrito(String codUsuario) {
        List<ShoppingCart> items = cartRepository.findByCodUsuario(codUsuario);
        List<Map<String,Object>> respuesta = new ArrayList<>();

        for (ShoppingCart item : items) {
            Optional<Producto> productoOpt = productoRepository.findById(item.getIdProducto());

            if (productoOpt.isPresent()) {
                Producto p = productoOpt.get();
                Map<String, Object> map = new HashMap<>();

                // ahora se armara el objeto con la misma estructura que esperaba el
                // frontend original en PHP
                map.put("idsc", item.getIdShoppingCart());
                map.put("idProducto", p.getIdProducto());
                map.put("nombre", p.getNombre());
                map.put("imagen", p.getImagen());
                map.put("precio", p.getPrecio());
                map.put("cantidad", item.getCantidad());

                // ahora hacemos la operacion para calcular el subtotal el cual va hacer precio * cantidad
                BigDecimal subTotal = p.getPrecio().multiply(new BigDecimal(item.getCantidad()));
                map.put("subTotal", subTotal);
                respuesta.add(map);
            }
        }
        return respuesta;
    }

    // agregar al carrito un producto
    public void agregarItem(String codUsuario, Long idProducto, Integer cantidad) {
        // verificamos si el producto ya esta en el carrito del usuario
        ShoppingCart existente = cartRepository.findByCodUsuarioAndIdProducto(codUsuario, idProducto);

        if (existente != null) {
            // si ya existe, solo sumamos la nueva cantidad o el nuevo producto
            existente.setCantidad(existente.getCantidad() + cantidad);
            cartRepository.save(existente);
        } else  {
            ShoppingCart nuevo = new ShoppingCart();
            nuevo.setCodUsuario(codUsuario);
            nuevo.setIdProducto(idProducto);
            nuevo.setCantidad(cantidad);
            cartRepository.save(nuevo);
        }
    }

    // eliminar un producto especifico
    public void eliminarItem(Long idShoppingCart) {
        cartRepository.deleteById(idShoppingCart);
    }

    // vaciar todo el carrito
    public void vaciarCarrito(String codUsuario) {
        cartRepository.deleteByCodUsuario(codUsuario);
    }
}
