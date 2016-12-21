package com.uagrm.emprendecruz.emarket.Presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Rect;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.github.snowdream.android.widget.SmartImageView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.uagrm.emprendecruz.emarket.Configuracion;
import com.uagrm.emprendecruz.emarket.Modelo.producto;
import com.uagrm.emprendecruz.emarket.PedidoActivity;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Util.CircleTransform;
import com.uagrm.emprendecruz.emarket.Util.ListCarrito;
import com.uagrm.emprendecruz.emarket.Util.UsuarioFacebook;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Principal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    TextView nombre;
    TextView correo;
    SmartImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nombre = (TextView) headerView.findViewById(R.id.TvNombreCliente);
        correo = (TextView) headerView.findViewById(R.id.TvCorreoDrawer);
        image = (SmartImageView) headerView.findViewById(R.id.IVSmartPerfil);
        ObtenerListaproductos();
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                //fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_cuenta:
                //fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_categorias:

                fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, Configuracion.class));
                break;
            case R.id.item_pedidos:
                //startActivity(new Intent(this, ActividadPedido.class));
                break;
            case R.id.item_logout:
                goLoginScreen();
                break;
            case R.id.item_ListPedidos:
                // startActivity(new Intent(this, ListPedidosActivity.class));
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                correo.setText("" + UsuarioFacebook.getUser().getEmail());
                nombre.setText(UsuarioFacebook.getUser().getName().toUpperCase());
                Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + UsuarioFacebook.getUser().getProvider_user_id() + "/picture?type=normal").transform(new CircleTransform()).into(image);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_carrito:
                cambiarActividad();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void cambiarActividad(){
        startActivity(new Intent(this,PedidoActivity.class));
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    private void goLoginScreen() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    public void ObtenerListaproductos(){

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


}
