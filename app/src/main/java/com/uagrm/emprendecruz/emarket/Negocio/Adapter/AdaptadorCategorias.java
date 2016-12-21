package com.uagrm.emprendecruz.emarket.Negocio.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.snowdream.android.widget.SmartImageView;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Modelo.producto;
import com.uagrm.emprendecruz.emarket.Util.Carrito;
import com.uagrm.emprendecruz.emarket.Util.ListCarrito;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias
        extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {

ViewGroup viewgroup;

    private  List<producto> items;
    Context context;
String name="";

    public AdaptadorCategorias(Context ctx) {
        this.context = ctx;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public SmartImageView imagen;
        public ImageView overflow;
        public ViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.TvDescripcion);
            precio = (TextView) v.findViewById(R.id.TvPrecio);
            overflow = (ImageView) v.findViewById(R.id.overflow);
            imagen = (SmartImageView) v.findViewById(R.id.miniatura_comida);
        }
    }


    public AdaptadorCategorias(List<producto> items, Context s, ViewGroup v) {
        this.items = items;
        this.context=s;
        this.viewgroup=v;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_categoria, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final producto item = items.get(i);

        viewHolder.nombre.setText(""+item.getDescripcion());
        viewHolder.precio.setText("$ "+item.getPrecio());


        Rect rec=new Rect(viewHolder.imagen.getLeft(),viewHolder.imagen.getTop(),viewHolder.imagen.getRight(),viewHolder.imagen.getBottom());
        viewHolder.imagen.setImageUrl("http://54.201.162.73/supermercado/images/productos/"+item.getImgPro(),rec);


        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopupMenu(viewHolder.overflow,item.getDescripcion());
            }
        });


    }
    private void showPopupMenu(View view,String idGrupo) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(idGrupo));
        popup.show();
    }

    class MyMenuItemClickListener  implements PopupMenu.OnMenuItemClickListener {

        String IdGrupos;
        public MyMenuItemClickListener(String idgrup) {
            this.IdGrupos=idgrup;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_contenido:
                  ObtenerDatos(IdGrupos);


                    return true;
                case R.id.action_horario:
                  EliminarProductoCarrito(IdGrupos);
                    return true;
                default:
            }
            return false;
        }
    }

    public android.app.AlertDialog createSimpleDialogEliminar(String titulo, String contenido,final String comida) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        builder.setTitle(""+titulo)
                .setMessage(""+contenido)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int i=0;
                                while (i<ListCarrito.getList().size()) {
                                    if (ListCarrito.getList().get(i).getComida().getDescripcion().equals(comida)) {
                                        ListCarrito.getList().remove(i);
                                        Toast.makeText(context, "Item de producto: " + name + " Fue Eliminado Del Carrito", Toast.LENGTH_SHORT).show();

                                    }
                                    i++;
                                    if (ListCarrito.getList().size()==0){
                                        ListCarrito.getMenu().getItem(0).setIcon(context.getResources().getDrawable(R.drawable.carrito_compras));

                                    }
                                }
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onNegativeButtonClick();
                            }
                        });

        return builder.create();
    }
    public void EliminarProductoCarrito(String name){
        if (ExisteListaCarrito(ObtenerComida(name))){

            createSimpleDialogEliminar("EASYMARKET","Desea Eliminar el Item de "+name,name).show();
        }else{
            Toast.makeText(context,"Este Item No Existe en el Carrito",Toast.LENGTH_SHORT).show();
        }

    }
    public Boolean ExisteListaCarrito(producto co){

        int i=0;
        while(i<ListCarrito.getList().size()){
            if(co.getDescripcion().equals(ListCarrito.getList().get(i).getComida().getDescripcion())){
                return true;
            }
            i++;
        }
        return false;
    }
    public producto ObtenerComida(String name){
        List<producto> Bebidas=ListCarrito.getListProductoBebidas();
        for (int i=0;i<Bebidas.size();i++){
           if(Bebidas.get(i).getDescripcion().equals(name)){
               return Bebidas.get(i);
           }
        }
        List<producto> Carnes=ListCarrito.getListProductoCarnes();
        for (int i=0;i<Carnes.size();i++){
            if(Carnes.get(i).getDescripcion().equals(name)){
                return Carnes.get(i);
            }
        }
        List<producto> Postres=ListCarrito.getListProductoVerduras();
        for (int i=0;i<Postres.size();i++){
            if(Postres.get(i).getDescripcion().equals(name)){
                return Postres.get(i);
            }
        }
        return null;
    }
    public void ObtenerDatos(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(""+name);
        this.name=name;
        // I'm using fragment here so I'm using getView() to provide ViewGroup
        // but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialogs_cantidad,viewgroup, false);
        // Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);
        final producto comida=ObtenerComida(name);
        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String data=input.getText().toString();
                if (isNumeric(data)) {

                    int m_Text = Integer.parseInt(input.getText().toString());

                    if (m_Text > 0) {
                        if (!ExisteListaCarrito(comida)) {
                            ListCarrito.setPrecioCarritoTotal(ListCarrito.getPrecioCarritoTotal()+(int)(comida.getPrecio()*m_Text));
                            ListCarrito.getList().add(new Carrito(comida, m_Text));
                            if (ListCarrito.getList().size()==1){
                           //     ListCarrito.getMenu().getItem(0).setIcon(context.getResources().getDrawable(R.drawable.carrito_compras2));
                            }
                            Toast.makeText(context, comida.getDescripcion() + " Agregada al Carrito", Toast.LENGTH_SHORT).show();

                        } else {
                            createSimpleDialog("Este Item ya Existe", "Desea Editar La cantidad de " + comida.getDescripcion() + " ?", comida, m_Text).show();
                        }
                    }else{

                        Toast.makeText(context,"Inserte Una Cantidad Valida",Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(context,"Inserte Un Numero Valido",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public  boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    public void EditarCantidadComida(producto con,int Cant){
        int i=0;
        while (i<ListCarrito.getList().size()){
            if (con.getDescripcion().equals(ListCarrito.getList().get(i).getComida().getDescripcion())){
                ListCarrito.getList().get(i).setCantidad(Cant);
            }
            i++;
        }
    }
    public android.app.AlertDialog createSimpleDialog(String titulo, String contenido,final producto comida, final int cant) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        builder.setTitle(""+titulo)
                .setMessage(""+contenido)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditarCantidadComida(comida,cant);

                             Toast.makeText(context,"Item Modificado Exitosamente ..",Toast.LENGTH_SHORT).show();

                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onNegativeButtonClick();
                            }
                        });

        return builder.create();
    }
}