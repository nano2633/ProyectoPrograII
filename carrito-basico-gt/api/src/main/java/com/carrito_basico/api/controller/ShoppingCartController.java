package com.carrito_basico.api.controller;

import com.carrito_basico.api.model.ShoppingCart;
import com.carrito_basico.api.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// En esta clase se creara la puerta de entrada para el frontend, este controlador
// recibira las peticiones HTTP, GET, POST, DELETE, y devolvera los datos en JSON
// y de esta manera se cumplira el estandar de una API RESTful.
@RestController
@RequestMapping("/api/carrito")
public class ShoppingCartController {
    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    // para ver el carrito de un usuario
    @GetMapping("/{codUsuario}")
    public List<Map<String, Object>> verCarrito(@PathVariable String codUsuario) {
        return cartService.obtenerCarrito(codUsuario);
    }

    // ahora se agregara un producto al carrito
    @PostMapping("/{codUsuario}/agregar")
    public ResponseEntity<String> agregarProducto(
            @PathVariable String codUsuario,
            @RequestBody ItemRequest peticion) {
        cartService.agregarItem(codUsuario, peticion.getIdProducto(), peticion.getCantidad());
        // aqui se devuelve un codigo de exito 200 ok
        return ResponseEntity.ok("{\"mensaje\": \"Producto agregado exitosamente\"}");
    }

    // eliminar un producto especifico
    @DeleteMapping("/eliminar/{idShoppingCart}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long idShoppingCart) {
        cartService.eliminarItem(idShoppingCart);
        return ResponseEntity.ok("{\"mensaje\": \"Producto eliminado del carrito\"}");
    }

    // vaciar completamente el carrito
    @DeleteMapping("/vaciar/{codUsuario}")
    public ResponseEntity<String> vaciarCarrito(@PathVariable String codUsuario) {
        cartService.vaciarCarrito(codUsuario);
        return ResponseEntity.ok("{\"mensaje\": \"Carrito vaciado por completo\"}");
    }

    // DTO Data Transfer Object, para recibir el JSON del frontend
    public static class ItemRequest {
        private Long idProducto;
        private Integer cantidad;

        public Long getIdProducto() { return idProducto; }
        public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    }
}
