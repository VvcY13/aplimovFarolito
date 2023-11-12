package com.example.app_firebase.Entidades;

public class DetalleComanda {
    private String nombreProducto;
    private int idProducto;
    private int cantidad;
    private double subtotal;

    private String comentario;

    public DetalleComanda() {
    }

    public DetalleComanda(int idProducto,String nombreProducto, int cantidad,String comentario, double subtotal) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.comentario = comentario;
        this.subtotal = subtotal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "DetalleComanda{" +
                "idProducto=" + idProducto +
                "nombre=" + nombreProducto +
                ", cantidad=" + cantidad +
                ", comentario=" + comentario +
                ", subtotal=" + subtotal +
                '}';
    }
}
