package com.uagrm.emprendecruz.emarket.Modelo;

/**
 * Created by Marco Antonio Mamani on 20/10/2016.
 */
public class pedido {
   int idPedido;
    int idCliente;
    int idEnviador;
    double latitud;
    double longitud;
    String fecha;
    Character estado;
    float monto;

    public pedido() {
    }

    public pedido(int idPedido, int idCliente, int idEnviador, double latitud, double longitud, String fecha, Character estado, float monto) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idEnviador = idEnviador;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.estado = estado;
        this.monto = monto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEnviador() {
        return idEnviador;
    }

    public void setIdEnviador(int idEnviador) {
        this.idEnviador = idEnviador;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}
