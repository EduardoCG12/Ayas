package com.dosdeemetres.ayashome;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.dosdeemetres.ayashome.Values;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleSignInClient mGoogleSignInClient;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        // Forzamos que sea siempre en Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // enganches
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        Button btnInvitado = findViewById(R.id.btnInvitado);
        imgLogo = findViewById(R.id.imageViewLogotipo);

        /*
            Configuracion de las opciones del login
            para pedir los datos del usuario, ID, mail y datos basicos del perfil
            El ID y los datos basicos estan incluidos en DEFAULT_SIGN_IN.
         */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Construimos un GoogleSignInClient con las opciones especificadas en el gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // botones
        signInButton.setOnClickListener(this);
        btnInvitado.setOnClickListener(this);

        // Hilo para descargar la imagen del logo
        String url = getString(R.string.login_url);
        HiloDescargarImagen hiloDescargarImagen = new HiloDescargarImagen(url);
        hiloDescargarImagen.start();
    }

    public class HiloDescargarImagen extends Thread {
        private final String url;
        private Bitmap bitmap;

        public HiloDescargarImagen(String url) {
            this.url = url;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            // cargamos la foto desde la URL y la guardamos en un Bitmap
            // para setearlo en la UI en el Hilo principal
            FutureTarget<Bitmap> futureTarget =
                    Glide.with(getApplicationContext())
                            .asBitmap()
                            .load(url)
                            .submit(600, 600);
            try {
                bitmap = futureTarget.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            //necesario para poder usar los elementos visuales (view) y modificarlos
            // seteamos la imagen descargada en el imageView
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgLogo.setImageBitmap(bitmap);

                }
            });
        }
    }


    // acciones botones
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btnInvitado:
                Intent intent = new Intent (this, MainActivity.class);
                startActivityForResult(intent, Values.RC_MAIN_ACTIVITY);
                break;
        }
    }

    // Se lanza el activity propio del login de google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Values.RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Resultado devuelto por el Intent de login de Google
        if (requestCode == Values.RC_SIGN_IN) {
            // La Task devuelta esta siempre completa, no hace falta ponerle un listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        // comprobar si el intento de inicio de sesion ha sido exitoso
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Se ha logueado correctamente, actualizamos la UI con la cuenta devuelta
            updateUI(account);
        } catch (ApiException e) {
            // Error en el login, devuelve el codigo de error
            Log.w(Values.LOG_TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        // si se ha logueado correctamente no sera null
        if (account != null){
            // lanzamos la activity Principal
            Intent intent = new Intent (this, MainActivity.class);
            startActivityForResult(intent, Values.RC_MAIN_ACTIVITY);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Comprobamos si el usuario sigue logueado (cuando cerro la aplicacion por ultima vez, no cerro sesion)
        // Si el usuario sigue logueado, la cuenta no sera null
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }
}