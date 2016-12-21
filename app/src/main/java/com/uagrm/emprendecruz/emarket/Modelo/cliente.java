package com.uagrm.emprendecruz.emarket.Modelo;

/**
 * Created by Marco Antonio Mamani on 17/10/2016.
 */
public class cliente {
    int idCliente;
    String nombre;
    String correo;
    String contrasena;
    String direccion;
    String telefono;
    String imgCli;

    public cliente() {
    }

    public cliente(int idCliente, String nombre, String correo, String contrasena, String direccion, String telefono, String imagen) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.imgCli = imagen;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getImagen() {
        return imgCli;
    }

    public void setImagen(String imagen) {
        this.imgCli = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdcliente() {
        return idCliente;
    }

    public void setIdcliente(int idcliente) {
        this.idCliente = idcliente;
    }
}
