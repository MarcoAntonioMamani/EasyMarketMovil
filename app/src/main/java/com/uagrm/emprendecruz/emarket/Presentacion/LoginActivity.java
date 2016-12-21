package com.uagrm.emprendecruz.emarket.Presentacion;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uagrm.emprendecruz.emarket.Modelo.Enviador;
import com.uagrm.emprendecruz.emarket.Modelo.Usuario;
import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Util.UsuarioFacebook;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Cosio on 20/10/2016.
 */
public class LoginActivity extends AppCompatActivity {

    EditText etUser, etPassword;
    Button btIngresar;
    String idEnviador;
    TextView Registrate;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.et_user_login);
        etPassword = (EditText) findViewById(R.id.et_password_login);
        btIngresar = (Button) findViewById(R.id.bt_ingresar_login);
        Registrate = (TextView) findViewById(R.id.Register);
        Registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSimpleDialog("EASY MARKET", "¿Desea crear una cuenta nueva?").show();
            }
        });
        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etUser.getText().toString().isEmpty()) {
                    if (!etPassword.getText().toString().isEmpty()) {
                         verificarDatos(etUser.getText().toString(), etPassword.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Ingrese su Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese su Usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.loginButtonFacebook);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel Login Facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public AlertDialog createSimpleDialog(String titulo, String contenido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("" + titulo)
                .setMessage("" + contenido)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                cambiarActividadCreateCuenta();
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

    public void verificarDatos(String correo, String passw){
        final ProgressDialog progresdialog=new ProgressDialog(this);
        progresdialog.setTitle("Verificando Usuario ...");
        progresdialog.show();
        AsyncHttpClient client=new AsyncHttpClient();
        //+"&contrasena="+passw+""
        client.get("http://54.201.162.73/EasyMarket/login.php?email="+correo+"&password="+passw+"", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Enviador enviador = new Enviador();
                if (statusCode==200){
                    progresdialog.dismiss();
                    try{
                        JSONArray jsonarray=new JSONArray(new String(responseBody));
                        if (jsonarray.isNull(0)){
                            Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta" ,Toast.LENGTH_SHORT).show();
                            return;
                        }else{

                            for (int i=0;i<jsonarray.length();i++){
                                String json1 = ""+jsonarray.getJSONObject(i).toString();
                                Gson gson = new Gson();
                                Usuario clie =  gson.fromJson(json1, Usuario.class);

                                UsuarioFacebook.setUser(clie);
                                goMainScreen();
                            }
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

    public void cambiarActividadCreateCuenta() {
        startActivity(new Intent(this, CreateCuenta.class));
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    private void goMainScreen() {
        Intent i = new Intent(this, Principal.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
