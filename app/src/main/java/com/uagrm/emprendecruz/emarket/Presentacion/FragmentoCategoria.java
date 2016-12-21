package com.uagrm.emprendecruz.emarket.Presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Negocio.Adapter.AdaptadorCategorias;
import com.uagrm.emprendecruz.emarket.Util.ListCarrito;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoCategoria extends Fragment {

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;
private ViewGroup viewgroup;
    public static FragmentoCategoria nuevaInstancia(int indiceSeccion) {
        FragmentoCategoria fragment = new FragmentoCategoria();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);
    viewgroup=container;
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorCategorias(ListCarrito.getListProductoCarnes(),getActivity(),viewgroup);
                break;
            case 1:
                adaptador = new AdaptadorCategorias(ListCarrito.getListProductoBebidas(),getActivity(),viewgroup);
                break;
            case 2:
                adaptador = new AdaptadorCategorias(ListCarrito.getListProductoVerduras(),getActivity(),viewgroup);
                break;
        }

        reciclador.setAdapter(adaptador);

        return view;
    }


}