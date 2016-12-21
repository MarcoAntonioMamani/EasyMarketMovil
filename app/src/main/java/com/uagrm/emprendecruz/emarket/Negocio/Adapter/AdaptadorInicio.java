package com.uagrm.emprendecruz.emarket.Negocio.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uagrm.emprendecruz.emarket.Modelo.Comida;
import com.uagrm.emprendecruz.emarket.R;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.TvDescripcionFavorito);
            precio = (TextView) v.findViewById(R.id.TvPrecioFavorito);
            imagen = (ImageView) v.findViewById(R.id.comidafavorito);
        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return Comida.COMIDAS_POPULARES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_favorito, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = Comida.COMIDAS_POPULARES.get(i);

        viewHolder.nombre.setText(item.getNombre().toUpperCase());
        viewHolder.precio.setText("$ "+item.getPrecio());
        viewHolder.imagen.setImageResource(item.getIdDrawable());


    }


}