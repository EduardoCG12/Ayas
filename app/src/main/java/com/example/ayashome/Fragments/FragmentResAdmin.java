package com.example.ayashome.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.ayashome.Clases.Reservas;
import com.example.ayashome.Clases.Values;
import com.example.ayashome.LoginActivity;
import com.example.ayashome.MainActivity;
import com.example.ayashome.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class FragmentResAdmin extends AppCompatActivity {
//Linea de las reservas del admin
    private GoogleSignInClient mGoogleSignInClient;

    private LinearLayout linearLayoutPrincipal, linearLayoutSecundario;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resadmin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_account_circle_24));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        linearLayoutPrincipal = findViewById(R.id.linearPrincipal);

        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //hacemos la consulta
        db.collection("Reservas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                //hemos creado un constructor con el objeto tipo QueryDocumentSnapshot
                                Reservas reservas = new Reservas(document);

                                //Log.e(Values.LOG_TAG, );

                                Log.e(Values.LOG_TAG, document.getId() + " => " + reservas.getHora());
                                anadirReservaUI(reservas);
                            }
                        } else {
                            Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                        }
                    }
                });

    }

    private void anadirReservaUI(Reservas reservas) {

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvFecha = new TextView(this);
        TextView tvHora = new TextView(this);

        tvFecha.setText(String.valueOf(reservas.getFecha().toDate()));
        tvFecha.setTextSize(12);
        tvHora.setText(reservas.getHora());
        tvHora.setTextSize(25);


        linearLayout.addView(tvFecha);
        linearLayout.addView(tvHora);

        linearLayoutPrincipal.addView(linearLayout);

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
                Intent intent = new Intent(FragmentResAdmin.this, MainActivity.class);
                startActivityForResult(intent, Values.REQ_ACT_2);
                return true;
            case R.id.action_reserva:
                action(R.string.reserva);
                return true;
            case R.id.action_logout:
                action(R.string.logOut);
                signOut();
                Intent intent2 = new Intent(FragmentResAdmin.this, LoginActivity.class);
                startActivityForResult(intent2, Values.REQ_ACT_2);
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
