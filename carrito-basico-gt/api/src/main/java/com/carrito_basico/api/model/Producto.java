package com.carrito_basico.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

// Esta clase representará la tabla en la base de datos
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`idProducto`")
    private Long idProducto;

    private String nombre;
    private String codigo;
    private String imagen;
    private BigDecimal precio;

    // JPA exige un constructor vacio
    public Producto() {

    }

    // Getters and Setters
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

}
