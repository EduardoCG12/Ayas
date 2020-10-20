package com.example.ayashome;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.ayashome.Clases.Values;
import com.example.ayashome.Fragments.ReservasAdminFragment;
import com.example.ayashome.Fragments.SeleccionFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Drawable yourDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_account_circle_48));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        LoginActivity.mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        // cargar imagen de google
        if (acct != null){
            Uri foto = acct.getPhotoUrl();
            System.out.println(foto);
            
        }

        // CARGAMOS EL FRAGMENT de seleccion de opciones
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, new SeleccionFragment())
                .commit();

    }

    //Esto es el llamado al menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Esto es la accion que hace los botones del menu
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_inicio:
                action(R.string.inicio);
                return true;
            case R.id.action_reserva:
                action(R.string.reserva);
                Intent intent = new Intent(MainActivity.this, ReservasAdminFragment.class);
                startActivityForResult(intent, Values.REQ_ACT_2);
                return true;
            case R.id.action_opciones:
                action(R.string.opciones);
                return true;
            case R.id.action_logout:
                action(R.string.logOut);
                signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        LoginActivity.mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Al hacer logout vuelve a la pantalla de login
                        Intent intent = new Intent ();
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }
}