package com.carrito_basico.api.service;

import com.carrito_basico.api.model.Producto;
import com.carrito_basico.api.model.ShoppingCart;
import com.carrito_basico.api.repository.ProductoRepository;
import com.carrito_basico.api.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    // Simulamos con @Mock, el repositorio para no tocar la base de datos real
    @Mock
    private ShoppingCartRepository cartRepository;

    // tambien simulamos con @Mock al repositorio del producto
    @Mock
    private ProductoRepository productoRepository;

    // Inyectamos el mock dentro del servicio que vamos a probar
    @InjectMocks
    private ShoppingCartService cartService;

    @Test
    void obtenerCarritoDebeConsultarLaBaseDeDatos() {
        // Preparar
        String usuarioTest = "usuario123";
        Long idProductoTest = 2L;

        // simulalmos el item en el carrito
        List<ShoppingCart> listaSimulada = new ArrayList<>();
        ShoppingCart item = new ShoppingCart();
        item.setIdShoppingCart(1L);
        item.setCodUsuario(usuarioTest);
        item.setIdProducto(idProductoTest);
        item.setCantidad(1);
        listaSimulada.add(item);

        // Simulamos el producto en el catalogo
        Producto productoSimulado = new Producto();
        productoSimulado.setIdProducto(idProductoTest);
        productoSimulado.setNombre("Producto Simulado");
        productoSimulado.setPrecio(new BigDecimal("100.00"));

        // Entenamos a los mock para cuando el servicio busque el carrito,
        // devuelve la lista simulada
        Mockito.when(cartRepository.findByCodUsuario(usuarioTest)).thenReturn(listaSimulada);
        // ahora Le decimos al repositorio de productos que devuelva nuestro producto simulado
        Mockito.when(productoRepository.findById(idProductoTest)).thenReturn(Optional.of(productoSimulado));
        // Ejecutar
        List<Map<String, Object>> resultado = cartService.obtenerCarrito(usuarioTest);

        // Verificar
        assertNotNull(resultado); // verificamos que no devuelva nulo
        // verificamos que se haya hecho la consulta a la base de datos exactamente 1 vez de ambos
        Mockito.verify(cartRepository, Mockito.times(1)).findByCodUsuario(usuarioTest);
        Mockito.verify(productoRepository, Mockito.times(1)).findById(idProductoTest);
    }

    @Test
    void agregarItemDebeGuardarElProductoEnLaBaseDeDatos() {
        // Preparar
        String usuarioTest = "usuario123";
        Long idProductoTest = 1L;
        Integer cantidadTest = 1;

        // simulamos que el producto no existe todavia en el carrito de este usuario
        Mockito.when(cartRepository.findByCodUsuarioAndIdProducto(usuarioTest, idProductoTest)).thenReturn(null);

        // Ejecutar
        cartService.agregarItem(usuarioTest, idProductoTest, cantidadTest);
        // Verificar
        Mockito.verify(cartRepository, Mockito.times(1)).save(Mockito.any(ShoppingCart.class));
    }

    @Test
    void eliminarItem() {
    }

    @Test
    void vaciarCarrito() {
        // Se prepara o se llama al ejemplo de usuario en este caso al usuario 123
        String usuarioTest = "usuario123";

        // Se ejecuta el llamado al método real del servicio vaciar carrito
        cartService.vaciarCarrito(usuarioTest);

        // Se verifica que el servicio haya ordenado al repositorio ejecutar el borrado exactamente 1 vez
        Mockito.verify(cartRepository, Mockito.times(1)).deleteByCodUsuario(usuarioTest);
    }
}