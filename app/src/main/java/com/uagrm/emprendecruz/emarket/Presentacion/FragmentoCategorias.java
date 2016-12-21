package com.uagrm.emprendecruz.emarket.Presentacion;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uagrm.emprendecruz.emarket.Modelo.producto;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Util.ListCarrito;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Marco Antonio Mamani on 21/12/2016.
 */

public class FragmentoCategorias extends Fragment {
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public FragmentoCategorias() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);

        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
    }

    private void poblarViewPager(ViewPager viewPager) {
        ObtenerListaproductos();
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(0), getString(R.string.titulo_tab_platillos));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(1), getString(R.string.titulo_tab_bebidas));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(2), getString(R.string.titulo_tab_verduras));
        viewPager.setAdapter(adapter);
    }

    public void ObtenerListaproductos(){

        final ProgressDialog progresdialog=new ProgressDialog(getContext());
        progresdialog.setTitle("Obteniendo Productos del Servidor ....");
        progresdialog.show();
        AsyncHttpClient client=new AsyncHttpClient();

        client.get("http://54.201.162.73/EasyMarket/mostrar_producto_verduras.php", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode==200){
                    //  progresdialog.dismiss();
                    try{
                        ListCarrito.setListProductoVerduras(new ArrayList<producto>());
                        JSONArray jsonarray=new JSONArray(new String(responseBody));
                        for (int i=0;i<jsonarray.length();i++){
                            String json1 = ""+jsonarray.getJSONObject(i).toString();
                            Gson gson = new Gson();
                            producto produ =  gson.fromJson(json1, producto.class);
                            ListCarrito.getListProductoVerduras().add(produ);

                            //  mensajeLogin(""+clie.getNombre());

                            //   cambiarActividad();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                ListCarrito.setLogeo(true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        AsyncHttpClient client2=new AsyncHttpClient();

        client2.get("http://54.201.162.73/EasyMarket/mostrar_producto_bebidas.php", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode==200){
                    //   progresdialog.dismiss();
                    try{
                        ListCarrito.setListProductoBebidas(new ArrayList<producto>());
                        JSONArray jsonarray=new JSONArray(new String(responseBody));
                        for (int i=0;i<jsonarray.length();i++){
                            String json1 = ""+jsonarray.getJSONObject(i).toString();
                            Gson gson = new Gson();
                            producto produ =  gson.fromJson(json1, producto.class);
                            ListCarrito.getListProductoBebidas().add(produ);

                            //  mensajeLogin(""+clie.getNombre());

                            //   cambiarActividad();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        AsyncHttpClient client3=new AsyncHttpClient();

        client3.get("http://54.201.162.73/EasyMarket/mostrar_producto_carnes.php", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode==200){
                    progresdialog.dismiss();
                    try{
                        ListCarrito.setListProductoCarnes(new ArrayList<producto>());
                        JSONArray jsonarray=new JSONArray(new String(responseBody));
                        for (int i=0;i<jsonarray.length();i++){
                            String json1 = ""+jsonarray.getJSONObject(i).toString();
                            Gson gson = new Gson();
                            producto produ =  gson.fromJson(json1, producto.class);
                            ListCarrito.getListProductoCarnes().add(produ);

                            //  mensajeLogin(""+clie.getNombre());

                            //   cambiarActividad();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }
}
