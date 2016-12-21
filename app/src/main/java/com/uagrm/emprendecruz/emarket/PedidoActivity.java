package com.uagrm.emprendecruz.emarket;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.uagrm.emprendecruz.emarket.Presentacion.Principal;
import com.uagrm.emprendecruz.emarket.Util.Carrito;
import com.uagrm.emprendecruz.emarket.Util.ListCarrito;

import java.util.List;

public class PedidoActivity extends AppCompatActivity {
    TableLayout columnProducto;
    TableLayout columnCantidad;
    TableLayout columnPrecio;
    private ProgressDialog pDialog;
    Menu menu;
    private MiTareaAsincronaDialog tarea2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        columnProducto=(TableLayout)findViewById(R.id.ColumnProducto);
        columnCantidad=(TableLayout)findViewById(R.id.ColumnCantidad);
        columnPrecio=(TableLayout)findViewById(R.id.ColumnPrecio);



        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.contenedor_pedido, new FragmentoPedido());
        ft.commit();
        agregarToolbar();
        CargarColumnProducto();
        CargarColumnCantidad();
        CargarColumnPrecio();
        CargarColumnPrecioTotal();

        if(ListCarrito.getList().size()>0){
      //      ListCarrito.getMenu().getItem(0).setIcon(getResources().getDrawable(R.drawable.carrito_compras2));

        }else{
     //       ListCarrito.getMenu().getItem(0).setIcon(getResources().getDrawable(R.drawable.carrito_compras));

        }
    }



    public void CargarColumnProducto(){
        TableLayout ll = (TableLayout) findViewById(R.id.ColumnProducto);

        List<Carrito> comi=ListCarrito.getList();
        for (int i = 0; i <= ListCarrito.getList().size(); i++) {


            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if (i==0){
                TextView tv = new TextView(this);
                tv.setText("PRODUCTO");
                tv.setTextSize(15);
                row.addView(tv);
                ll.addView(row,i);
            }else{
                TextView tv = new TextView(this);
                String name=ListCarrito.getList().get(i-1).getComida().getDescripcion();
                tv.setText(""+ListCarrito.getList().get(i-1).getComida().getDescripcion());
                row.addView(tv);
                ll.addView(row,i);

            }
        }
    }

    public void CargarColumnCantidad(){
        TableLayout ll = (TableLayout) findViewById(R.id.ColumnCantidad);


        for (int i = 0; i <ListCarrito.getList().size()+1; i++) {


            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if (i==0){
                TextView tv = new TextView(this);
                tv.setText("CANTIDAD");
                tv.setTextSize(15);
                row.addView(tv);
                ll.addView(row,i);
            }else {
                if (i <= ListCarrito.getList().size()) {
                    TextView tv = new TextView(this);
                    tv.setText("" + ListCarrito.getList().get(i - 1).getCantidad());
                    row.addView(tv);
                    ll.addView(row, i);
                }
            }
        }
    }




    public void CargarColumnPrecio(){
        TableLayout ll = (TableLayout) findViewById(R.id.ColumnPrecio);


        for (int i = 0; i <= ListCarrito.getList().size()+1; i++) {


            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if (i==0){
                TextView tv = new TextView(this);
                tv.setText("PRECIO");
                tv.setTextSize(15);
                row.addView(tv);
                ll.addView(row,i);
            }else {
                if (i <= ListCarrito.getList().size()) {
                    TextView tv = new TextView(this);
                    tv.setText("" + ListCarrito.getList().get(i - 1).getComida().getPrecio());

                    row.addView(tv);
                    ll.addView(row, i);
                }else{
                    TextView tv = new TextView(this);
                    tv.setText("TOTAL: ");
                    row.addView(tv);
                    ll.addView(row, i);

                }
            }
        }
    }

    public void CargarColumnPrecioTotal(){
        TableLayout ll = (TableLayout) findViewById(R.id.ColumnPrecioTotal);
        int total=0;

        for (int i = 0; i <= ListCarrito.getList().size()+1; i++) {


            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if (i==0){
                TextView tv = new TextView(this);
                tv.setText("SUBTOTAL");
                tv.setTextSize(15);
                row.addView(tv);
                ll.addView(row,i);
            }else {
                if (i <= ListCarrito.getList().size()) {
                    TextView tv = new TextView(this);
                    int subtotal=ListCarrito.getList().get(i-1).getCantidad()*(int)ListCarrito.getList().get(i-1).getComida().getPrecio();
                    tv.setText("" + subtotal);
                    total=total+subtotal;
                    row.addView(tv);
                    ll.addView(row, i);
                }else{
                    TextView tv = new TextView(this);
                    tv.setText(ListCarrito.getPrecioCarritoTotal()+" $");
                    row.addView(tv);
                    ll.addView(row, i);

                }
            }
        }
    }
    public void onClickTost(View v){
        createSimpleDialog("EASYMARKET","Desea Enviar La Lista de Pedidos ?").show();
        ;
    }

    public void mensaje(String cadena) {
        Toast.makeText(this,cadena,Toast.LENGTH_SHORT).show();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public AlertDialog createSimpleDialog(String titulo, String contenido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(""+titulo)
                .setMessage(""+contenido)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(ListCarrito.getList().size()>0) {

                                    cambiarActividadMapa();
                                    //  EjecutarCarga("Justificando");
                                    //ListCarrito.setList(new ArrayList<Carrito>());
                                    // ListCarrito.setPrecioCarritoTotal(0);
                                }else{
                                    Toast.makeText(getApplicationContext(),"No Existe Ningun Item Para Solicitar el pedido",Toast.LENGTH_SHORT).show();
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

    public void EjecutarCarga(String Cad){
        pDialog = new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage( "Enviando Datos Al Servidor...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);

        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }

    public static class FragmentoPedido extends PreferenceFragment {

        public FragmentoPedido() {
            // Constructor Por Defecto
        }

        //  @Override
       /* public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferencias);
        }*/


    }
    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }
    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=5; i++) {
                tareaLarga();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();

                cambiarActividad();
                Toast.makeText(getApplicationContext(), "Pedido Enviado!", Toast.LENGTH_SHORT).show();



            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarActividad(){
        finish();
        startActivity(new Intent(this,Principal.class));
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }
    public void cambiarActividadMapa(){
      //  finish();
     //   startActivity(new Intent(this,MapaPedido.class));
      //  overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }
}
