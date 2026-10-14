import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductoService } from './services/producto.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  styleUrl: './app.component.css',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {
  productos: any[] = []; // Variable para almacenar los productos obtenidos del backend
  itemsCarrito: any[] = []; // Variable para almacenar los productos que estan en el carrito del usuario
  vistaActual: string = 'catalogo'; // Variable para controlar la vista actual (catalogo o carrito)

  constructor(private productoService: ProductoService) {}

// ngOnInit es un hook del ciclo de vida de Angular que se ejecuta después de que el 
// componente ha sido inicializado. Aquí, lo usamos para llamar al servicio y obtener
//  los productos desde el backend.
  ngOnInit(): void {
this.cargarCatalogo();
  }
  // Metodo para cargar el catalogo de productos desde el backend.  Lo que hace 
  // es llamar al servicio para obtener los productos y almacenarlos en la variable 
  // productos.
  cargarCatalogo(): void {
    this.vistaActual = 'catalogo'; // Cambiamos la vista a catalogo
    this.productoService.obtenerProductos().subscribe({ // Nos suscribimos al Observable que retorna el servicio para obtener los productos
      next: (data) => this.productos = data, // Cuando la solicitud HTTP se complete exitosamente, almacenamos los productos en la variable productos
      error: (err) => console.error('Error al cargar catálogo:', err) // Si ocurre un error al hacer la solicitud HTTP, lo mostramos en la consola
    });
  }

  // Metodo para agregar un producto al carrito.  Lo que hace es llamar al servicio 
  // para agregar el producto al carrito y mostrar un mensaje de exito o error.
  agregarProducto(idProducto: number): void {
    this.productoService.agregarAlCarrito(idProducto).subscribe({ // Nos suscribimos al Observable que retorna el servicio para agregar el producto al carrito
      next: () => alert('¡Producto agregado al carrito!'), // Cuando la solicitud HTTP se complete exitosamente, mostramos un mensaje de exito
      error: (err) => alert('Error al agregar el producto.') // Si ocurre un error al hacer la solicitud HTTP, mostramos un mensaje de error
    });
  }

  // Metodo para vaciar el carrito.  Lo que hace es vaciar la variable itemsCarrito y cambiar la vista a catalogo.
  vaciarCarrito(): void {
    this.productoService.vaciarCarrito().subscribe({
      next: (repuesta) => {
        // solo si el backend responde con exito, un 200 ok, vaciamos el carrito
        this.itemsCarrito = []; // Vaciamos la variable itemsCarrito
        alert('¡Carrito vaciado con éxito!'); // Mostramos un mensaje de exito
        this.vistaActual = 'catalogo'; // Cambiamos la vista a catalogo
      }, 
      error: (err) => { // si algo falla el suario se queda en el carrito y ve el error
        console.error('Error al vaciar carrito:', err); // Si ocurre un error al hacer la solicitud HTTP, lo mostramos en la consola
        alert('Hubo un error al vaciar el carrito. Por favor, inténtelo de nuevo.'); }
  });
  }

  // Metodo para ver los productos que estan en el carrito del usuario.  Lo que 
  // hace es llamar al servicio para obtener los productos del carrito y 
  // almacenarlos en la variable itemsCarrito.
  verCarrito(): void {
    this.productoService.obtenerCarrito().subscribe({ // Nos suscribimos al Observable que retorna el servicio para obtener los productos del carrito
      next: (data) => { // Cuando la solicitud HTTP se complete exitosamente, almacenamos los productos del carrito en la variable itemsCarrito
        this.itemsCarrito = data; // Almacenamos los productos del carrito en la variable itemsCarrito
        console.log('Datos del carrito:', this.itemsCarrito); // Mostramos los productos del carrito en la consola para verificar que se hayan obtenido correctamente
        this.vistaActual = 'carrito'; // Cambiamos la vista
      },
      error: (err) => console.error('Error al cargar carrito:', err) // Si ocurre un error al hacer la solicitud HTTP, lo mostramos en la consola
    });
  }

  // Metodo para calcular el total del carrito.  Lo que hace es sumar los 
  // subtotales de cada producto en el carrito y retornar el total.
  get totalCarrito(): number {
    return this.itemsCarrito.reduce((suma, item) => suma + item.subTotal, 0);
  }
}