package com.example.ayashome;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.example.ayashome.Clases.Values;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    static GoogleSignInClient mGoogleSignInClient;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // enganches
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        Button btnInvitado = findViewById(R.id.btnInvitado);
        imgLogo = findViewById(R.id.imageViewLogotipo);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // acciones botones
        signInButton.setOnClickListener(this);
        btnInvitado.setOnClickListener(this);

        // Hilo para descargar la imagen del logo
        String url = "https://ia601506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
        // String url = "https://ia601509.us.archive.org/4/items/logotipo_202010/logotipo.png";
        HiloDescargarImagen hiloDescargarImagen = new HiloDescargarImagen(url);
        hiloDescargarImagen.start();

        /*Glide.with(getApplicationContext())
                .load(url)
                .into(imgLogo);*/


    }

    public class HiloDescargarImagen extends Thread {
        private String url;
        private Bitmap bitmap;

        public HiloDescargarImagen(String url) {
            this.url = url;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            // cargamos la foto desde la URL

            FutureTarget<Bitmap> futureTarget =
                    Glide.with(getApplicationContext())
                            .asBitmap()
                            .load(url)
                            .submit(500, 500);
            try {
                bitmap = futureTarget.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           /* try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   imgLogo.setImageBitmap(bitmap);

                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btnInvitado:
                Intent intent = new Intent (this, MainActivity.class);
                startActivityForResult(intent, Values.RC_MAIN_ACTIVITY);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                }*/
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Values.RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Values.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        // comprobar si el intento de inicio de sesion ha sido exitoso
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(Values.LOG_TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        // si se ha logueado previamente no sera null
        if (account != null){
            // lanzamos la activity Principal
            Intent intent = new Intent (this, MainActivity.class);
            startActivityForResult(intent, Values.RC_MAIN_ACTIVITY);
            // TODO preguntar a Asier: hacer con esta ativity
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }
}