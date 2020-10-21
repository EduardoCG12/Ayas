package com.example.ayashome;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.invito));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
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
                Intent intent = new Intent(MainActivity.this, FragmentResAdmin.class);
                startActivityForResult(intent, Values.REQ_ACT_2);
                return true;
            case R.id.action_opciones:
                action(R.string.opciones);
                Intent intent2 = new Intent(MainActivity.this, Opciones.class);
                startActivityForResult(intent2, Values.REQ_ACT_2);
                return true;
            case R.id.action_logout:
                action(R.string.logOut);
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