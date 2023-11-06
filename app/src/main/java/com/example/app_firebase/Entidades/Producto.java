package com.example.app_firebase.Entidades;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private String categoriaProducto;
    private String tipoProducto;
    private Boolean disponibilidadProducto;

    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, Double precioProducto, String categoriaProducto, String tipoProducto, Boolean disponibilidadProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.categoriaProducto = categoriaProducto;
        this.tipoProducto = tipoProducto;
        this.disponibilidadProducto = disponibilidadProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Boolean getDisponibilidadProducto() {
        return disponibilidadProducto;
    }

    public void setDisponibilidadProducto(Boolean disponibilidadProducto) {
        this.disponibilidadProducto = disponibilidadProducto;
    }

    @Override
    public String toString() {
        return idProducto + " " + nombreProducto + " S/ " + precioProducto + " " + categoriaProducto + " " + disponibilidadProducto;
    }
}
