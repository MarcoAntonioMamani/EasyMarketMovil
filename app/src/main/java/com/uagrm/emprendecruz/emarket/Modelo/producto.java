package com.uagrm.emprendecruz.emarket.Modelo;

/**
 * Created by Marco Antonio Mamani on 20/10/2016.
 */
public class producto {

    int idPro;
    int idCat;
    int cantidad;
    float precio;
    String descripcion;
    String imgPro;

    public producto() {
    }

    public producto(int idPro, int idCat, int cantidad, float precio, String descripcion, String imgPro) {
        this.idPro = idPro;
        this.idCat = idCat;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imgPro = imgPro;
    }

    public String getImgPro() {
        return imgPro;
    }

    public void setImgPro(String imgPro) {
        this.imgPro = imgPro;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }
}
