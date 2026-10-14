package com.carrito_basico.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`idShoppingCart`")
    private Long idShoppingCart;

    @Column(name = "`idProducto`")
    private Long idProducto;

    @Column(name = "`codUsuario`")
    private String codUsuario;

    private Integer cantidad;

    // constructor vacio
    public ShoppingCart() {}

    // Guetters and Setters
    public Long getIdShoppingCart() { return idShoppingCart; }
    public void setIdShoppingCart(Long idShoppingCart) {this.idShoppingCart = idShoppingCart;}

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) {this.idProducto = idProducto;}

    public String getCodUsuario() { return codUsuario; }
    public void setCodUsuario(String codUsuario) {this.codUsuario = codUsuario;}

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
}
