package com.example.app_firebase.Entidades;

public class Administrador {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String usuario;

    public Administrador() {
    }

    public Administrador(int id, String nombre, String apellido, String correo, String contraseña, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.usuario = usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                " usuario='" + usuario + '\'' ;
    }
}
