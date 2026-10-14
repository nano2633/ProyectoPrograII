import { Injectable, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
    // Apuntamos a la URL del backend para obtener los productos
    private apiUrl = 'http://localhost:8080/api/productos';

    constructor(private http: HttpClient) { } // Inyectamos HttpClient para poder 
    // hacer solicitudes HTTP al backend

    // Metodo para obtener todos los productos desde el backend, que solicita el JSON.
    obtenerProductos(): Observable<any[]> { // Retornamos un Observable que emitirá un array de productos cuando la solicitud HTTP se complete
        return this.http.get<any[]>(this.apiUrl); // Hacemos una solicitud GET a la URL del backend para obtener los productos
    }

    // Metodo para agregar al carrito un producto.  Lo que hace es enviar una 
    // solicitud POST al backend con el id del producto que se quiere agregar al carrito.
    agregarAlCarrito(idProducto: number): Observable<any> {
        // Usamos el 'usuario123' como un ejemplo de usuario. En un caso real, este valor debería ser dinámico.
        const url = `http://localhost:8080/api/carrito/usuario123/agregar`; 
        // ^--Construimos la URL para agregar el producto al carrito, usando el id del producto.
        // Ahora se construira el cuerpo JSON (EL ItemRequest que espera Spring Boot) que contiene el id del producto que queremos agregar al carrito.
        const body = { idProducto: idProducto,
            cantidad: 2 // Por defecto, agregamos 1 unidad del producto al carrito. Esto puede ser modificado según la necesidad.
         }; // En otras palabras, creamos un objeto con el id del producto que queremos agregar al carrito.
        return this.http.post(url, body); // Enviamos la peticion POST y el cuerpo con los datos del producto a agregar al carrito.
    }

    // Metodo para traer los productos que estan en el carrito del usuario.  Lo que hace es enviar una solicitud GET al backend para obtener los productos que estan en el carrito del usuario.
    obtenerCarrito(): Observable<any[]> {
        const url = `http://localhost:8080/api/carrito/usuario123`; // Construimos la URL para obtener los productos del carrito del usuario.
        return this.http.get<any[]>(url); // Hacemos una solicitud GET a la URL del backend para obtener los productos del carrito.
    }

    // Metodo para vaciar el carrito del usuario.  Lo que hace es enviar una solicitud DELETE al backend para vaciar el carrito del usuario.
    vaciarCarrito(): Observable<any> {
        const url = `http://localhost:8080/api/carrito/vaciar/usuario123`; // Construimos la URL para vaciar el carrito del usuario.
        return this.http.delete(url); // Hacemos una solicitud DELETE a la URL del backend para vaciar el carrito.
    }
}
