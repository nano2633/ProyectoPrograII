package com.carrito_basico.api.service;

import com.carrito_basico.api.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    // Simulamos con @Mock, el repositorio para no tocar la base de datos real
    @Mock
    private ShoppingCartRepository cartRepository;

    // Inyectamos el mock dentro del servicio que vamos a probar
    @InjectMocks
    private ShoppingCartService cartService;

    @Test
    void obtenerCarrito() {
    }

    @Test
    void agregarItem() {
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