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
import com.squareup.picasso.Picasso;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Util.CircleTransform;
import com.uagrm.emprendecruz.emarket.Util.UsuarioFacebook;

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

                //fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_configuracion:
                //startActivity(new Intent(this, ActividadConfiguracion.class));
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
                //cambiarActividad();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goLoginScreen() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


}
