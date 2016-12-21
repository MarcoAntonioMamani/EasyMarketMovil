package com.uagrm.emprendecruz.emarket.Util;

import com.uagrm.emprendecruz.emarket.Modelo.producto;

/**
 * Created by Marco Antonio Mamani on 15/10/2016.
 */
public class Carrito {
    producto comida;
    int cantidad;

    public Carrito(producto comida, int cantidad) {
        this.comida = comida;
        this.cantidad = cantidad;
    }

    public producto getComida() {
        return comida;
    }

    public void setComida(producto comida) {
        this.comida = comida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
