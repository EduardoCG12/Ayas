package com.dosdeemetres.ayashome;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    // la cuenta es estatica para que sea accesible desde todos los fragments siguientes
    public static GoogleSignInAccount acct;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        // Forzamos que sea siempre en Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_account_circle_48));
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Recuperamos el inicio de sesion de google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        acct = GoogleSignIn.getLastSignedInAccount(this);

        // cargar imagen de google
        // si esta logueado y tiene alguna imagen
       if (acct != null && acct.getPhotoUrl() != null) {
            HiloDescargarImagen hiloDescargarImagen = new HiloDescargarImagen(acct.getPhotoUrl().toString());
            hiloDescargarImagen.start();
       }

        Fragment fragment;
        // Comprobamos si es el admin o es un usuario normal
        if (acct != null && Objects.requireNonNull(acct.getEmail()).equals("developer.ayashome@gmail.com")){
            // si es admin cargamos el FRAGMENT DE RESERVAS
            fragment = new ListaReservaFragment();
        }

        else{
            fragment = new MainFragment();
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

   /*     if (savedInstanceState!=null){
            if(savedInstanceState.getBoolean("key")){
                //recoger usuario
            }
        }*/

    }

    //Esto es el llamado al menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Esto es la accion que hace los botones del menu
   @SuppressLint("NonConstantResourceId")
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_inicio:
                // CARGAMOS EL FRAGMENT inicial
                Fragment fragment = new MainFragment();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.action_reserva:
                if (acct != null) {
                    fragment = new ListaReservaFragment();
                    fragmentManager = this.getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else {
                    Toast.makeText(this, "Debe loguearse como usuario para poder acceder", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_logout:
                // Deslogueamos al usuario actual
                signOut();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Al hacer logout vuelve a la pantalla de login
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }


    public class HiloDescargarImagen extends Thread {
        private final String url;
        private Drawable drawable;

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
                            .submit(96, 96);
            try {
                // guardamos el bitmap
                Bitmap bitmap = futureTarget.get();
                // lo hacemos redondeado la foto de google
                Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                Paint paint = new Paint();
                paint.setShader(shader);
                paint.setAntiAlias(true);
                Canvas c = new Canvas(circleBitmap);
                c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
                // lo convertimos a drawable
                drawable = new BitmapDrawable(getResources(), circleBitmap);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            //necesario para poder usar los elementos visuales (view) y modificarlos
            // seteamos la imagen descargada como incono del toolbar
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toolbar.setOverflowIcon(drawable);
                }
            });
        }
    }

}