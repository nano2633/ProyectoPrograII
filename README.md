# Sistema de Carrito de Compras Full-Stack (MVC)

Este proyecto es la migración y modernización de un sistema monolítico de carrito de compras (originalmente en PHP) hacia una arquitectura distribuida de 6 capas utilizando el patrón Modelo-Vista-Controlador (MVC). 

## Tecnologías Utilizadas
* **Frontend:** Angular (Arquitectura Standalone, enrutamiento, peticiones HTTP asíncronas).
* **Backend:** Java 17 + Spring Boot (Spring Web, Spring Data JPA).
* **Base de Datos:** MySQL Server.
* **Pruebas Automatizadas:** JUnit 5 y Mockito.
* **Infraestructura:** Despliegue local en máquina virtual Ubuntu Linux (VMware) con gestión de red y políticas CORS globales.

## Arquitectura del Backend
El servidor RESTful en Spring Boot está dividido estrictamente en capas para garantizar la separación de responsabilidades:
1. **Controladores (`@RestController`):** Interceptan peticiones HTTP (GET, POST, DELETE) y manejan los DTOs.
2. **Servicios (`@Service`):** Contienen la lógica de negocio (cálculo de cantidades, validación de existencia).
3. **Repositorios (`@Repository`):** Implementan el patrón DAO mediante `JpaRepository` para abstraer las consultas SQL.
4. **Modelos (`@Entity`):** Mapeo objeto-relacional (ORM) de las tablas `Producto` y `ShoppingCart`.

## Manual de Ejecución Local

### 1. Base de Datos
* Asegúrese de tener MySQL corriendo en el puerto `3306`.
* Verifique que las credenciales en `src/main/resources/application.properties` coincidan con su entorno.

### 2. Levantar el Backend (Spring Boot)
* Abra el proyecto Java en su IDE de preferencia.
* Actualice las dependencias de Maven (`pom.xml`).
* Ejecute la clase principal `ApiApplication.java`. El servidor iniciará en `http://localhost:8080`.

### 3. Levantar el Frontend (Angular)
* Abra una terminal y navegue a la carpeta `carrito-frontend`.
* Instale las dependencias ejecutando: `npm install`
* Levante el servidor de desarrollo: `ng serve -o`
* La aplicación se abrirá automáticamente en su navegador en `http://localhost:4200`.

## Pruebas Unitarias
El proyecto cuenta con pruebas automatizadas bajo el estándar AAA (Arrange, Act, Assert) evaluando el ciclo CRUD completo (lectura, escritura y eliminación) de la capa de servicios mediante entornos aislados con `@Mock`.
