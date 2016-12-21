package com.uagrm.emprendecruz.emarket.Util;

import android.view.Menu;

import com.uagrm.emprendecruz.emarket.Modelo.cliente;
import com.uagrm.emprendecruz.emarket.Modelo.pedido;
import com.uagrm.emprendecruz.emarket.Modelo.producto;
import com.uagrm.emprendecruz.emarket.Modelo.cliente;
import com.uagrm.emprendecruz.emarket.Modelo.pedido;
import com.uagrm.emprendecruz.emarket.Modelo.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Antonio Mamani on 15/10/2016.
 */
public class ListCarrito implements Serializable{
  public static  List<Carrito> list=new ArrayList<>();
    public static cliente clie=new cliente();
    public static int PrecioCarritoTotal=0;
public static Boolean logeo=false;
    public static List<producto> ListProductoVerduras=new ArrayList<>();
    public static List<producto> ListProductoBebidas=new ArrayList<>();
    public static List<producto> ListProductoCarnes=new ArrayList<>();

    public static double Longitud=0;
    public static double Latitud=0;


    public static pedido PedidoSeleccionado;

    public static pedido getPedidoSeleccionado() {
        return PedidoSeleccionado;
    }

    public static void setPedidoSeleccionado(pedido pedidoSeleccionado) {
        PedidoSeleccionado = pedidoSeleccionado;
    }

    public static double getLongitud() {
        return Longitud;
    }

    public static void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public static double getLatitud() {
        return Latitud;
    }

    public static void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public static List<producto> getListProductoVerduras() {
        return ListProductoVerduras;
    }

    public static void setListProductoVerduras(List<producto> listProductoVerduras) {
        ListProductoVerduras = listProductoVerduras;
    }

    public static List<producto> getListProductoBebidas() {
        return ListProductoBebidas;
    }

    public static void setListProductoBebidas(List<producto> listProductoBebidas) {
        ListProductoBebidas = listProductoBebidas;
    }

    public static List<producto> getListProductoCarnes() {
        return ListProductoCarnes;
    }

    public static void setListProductoCarnes(List<producto> listProductoCarnes) {
        ListProductoCarnes = listProductoCarnes;
    }

    public static int getPrecioCarritoTotal() {
        return PrecioCarritoTotal;
    }

    public static void setPrecioCarritoTotal(int precioCarritoTotal) {
        PrecioCarritoTotal = precioCarritoTotal;
    }

    public static Boolean getLogeo() {
        return logeo;
    }

    public static void setLogeo(Boolean logeo) {
        ListCarrito.logeo = logeo;
    }

    public static cliente getClie() {
        return clie;
    }

    public static void setClie(cliente clie) {
        ListCarrito.clie = clie;
    }

    public static Menu menu;

    public static Menu getMenu() {
        return menu;
    }

    public static void setMenu(Menu menu) {
        ListCarrito.menu = menu;
    }

    public static List<Carrito> getList() {
        return list;
    }

    public static void setList(List<Carrito> list) {
        ListCarrito.list = list;
    }
}
