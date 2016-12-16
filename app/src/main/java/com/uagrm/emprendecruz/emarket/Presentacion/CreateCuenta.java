package com.uagrm.emprendecruz.emarket.Presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.squareup.picasso.Picasso;

import com.uagrm.emprendecruz.emarket.R;
import com.uagrm.emprendecruz.emarket.Util.CircleTransform;
import com.uagrm.emprendecruz.emarket.Util.UsuarioFacebook;

import org.json.JSONObject;

import java.util.Arrays;

public class CreateCuenta extends AppCompatActivity {

    LoginButton buttonLoginFacebook;
    CallbackManager callbackManager;
    AutoCompleteTextView TvNameCreate, TvCorreoCreate;
    AutoCompleteTextView TvContrasenaA, TvContrasenaB;
    ImageView imageViewPhoto;
    Button Registrar;
    String idFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_create_cuenta);
        buttonLoginFacebook = (LoginButton) findViewById(R.id.login_buttonFacebook);

        imageViewPhoto = (ImageView) findViewById(R.id.faceperfil);

        TvNameCreate = (AutoCompleteTextView) findViewById(R.id.TvNameCreate);
        TvCorreoCreate = (AutoCompleteTextView) findViewById(R.id.TVcorreoCreate);
        TvContrasenaA = (AutoCompleteTextView) findViewById(R.id.TVContrasenaA);
        TvContrasenaB = (AutoCompleteTextView) findViewById(R.id.TVRepetirContrasenaB);

        Registrar = (Button) findViewById(R.id.BtnRegistrarCuenta);
        TvNameCreate.setEnabled(false);
        //  TvCorreoCreate.setEnabled(false);
        buttonLoginFacebook.setReadPermissions("user_friends");
        buttonLoginFacebook.setReadPermissions("public_profile");
        buttonLoginFacebook.setReadPermissions("email");
        buttonLoginFacebook.setReadPermissions("user_birthday");
        //Pedimos permiso para poder obtener el email
        buttonLoginFacebook.setReadPermissions("email");
        buttonLoginFacebook.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        //Registramos un callback se ejecutará una vez se hace introducido las credenciales
        //de la cuenta de facebook
        buttonLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Método usado para obtener los campos o atributos solciitados
                getFaceBookProfileDetails(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        ////////////////Button Registrar/////////////////////
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarCampos() && ContrasenaCorrectas()) {
                    cambiarActividadPrincipal();
                }
            }
        });
        cargarDatosFaxcebook();
    }

    public void cargarDatosFaxcebook() {
        if (UsuarioFacebook.getCorreoUsuario().length() > 0 && UsuarioFacebook.getIdFacebookUsuario().length() > 0 && UsuarioFacebook.getNameUsuario().length() > 0) {
            TvNameCreate.setText(UsuarioFacebook.getNameUsuario());
            TvCorreoCreate.setText(UsuarioFacebook.getCorreoUsuario());
        }
    }

    public boolean verificarCampos() {
        if (TvNameCreate.length() > 0 && TvCorreoCreate.length() > 0 && TvContrasenaA.length() > 0 && TvContrasenaB.length() > 0) {
            return true;
        } else {
            Toast.makeText(this, "Por Favor Rellene Todos Los Campos de Textos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean ContrasenaCorrectas() {
        if (TvContrasenaA.length() > 7 && TvContrasenaB.length() > 7) {
            String a = TvContrasenaA.getText().toString();
            String b = TvContrasenaB.getText().toString();
            //Toast.makeText(this,"a: "+a+"  b:"+b,Toast.LENGTH_SHORT).show();
            if (a.equals(b)) {
                return true;
            } else {
                Toast.makeText(this, "No Coinciden las Contraseñas vuelva a escribirlos", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Por Favor Introduzca una Contraseña con mas de 7 palabras o numeros", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Retorna la reppuesta después del ingreso de las credenciales de facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void getFaceBookProfileDetails(final AccessToken accessToken) {

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //object retorna lo indicado en paramters.putString("fields", "email") en este caso, solo contiene el email
            @Override
            public void onCompleted(final JSONObject object, GraphResponse response) {
                try {
                    //Profile clase que contiene las características báscias de la cuenta de facebook (No retorna email)
                    Profile profileDefault = Profile.getCurrentProfile();
                    //Librería usada para poder mostrar la foto de perfil de facebook con una transformación circular

                    TvNameCreate.setText("" + profileDefault.getFirstName() + " " + profileDefault.getLastName());
                    idFacebook = profileDefault.getId();
                    TvCorreoCreate.setText("" + object.getString("email"));
                    buttonLoginFacebook.setVisibility(View.INVISIBLE);
                    Picasso.with(CreateCuenta.this).load(profileDefault.getProfilePictureUri(350, 350)).transform(new CircleTransform()).into(imageViewPhoto);

                    UsuarioFacebook.setCorreoUsuario("" + object.getString("email"));
                    UsuarioFacebook.setIdFacebookUsuario(object.getString("id"));
                    UsuarioFacebook.setNameUsuario("" + profileDefault.getFirstName() + " " + profileDefault.getLastName());
                } catch (Exception e) {
                    Log.e("E-MainActivity", "getFaceBook" + e.toString());
                }
            }
        });
        Bundle parameters = new Bundle();
        //solicitando el campo email
        parameters.putString("fields", "id,name,link, email");
        request.setParameters(parameters);
        request.executeAsync();
    }



    public void cambiarActividadPrincipal() {
        finish();
        startActivity(new Intent(this, Principal.class));
        overridePendingTransition(R.transition.left_in, R.transition.left_out);

    }
}
