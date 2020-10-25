package com.dosdeemetres.ayashome.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.Clases.Values;
import com.dosdeemetres.ayashome.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class FragmentResAdmin extends AppCompatActivity {

    private LinearLayout linearLayoutPrincipal, linearLayoutSecundario;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resadmin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linearLayoutPrincipal = findViewById(R.id.linearPrincipal);

        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //hacemos la consulta
        db.collection("Reserva")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                //hemos creado un constructor con el objeto tipo QueryDocumentSnapshot
                                Reserva reserva = new Reserva(document);

                                //Log.e(Values.LOG_TAG, );

                                Log.e(Values.LOG_TAG, document.getId() + " => " + reserva.getHora());
                                anadirReservaUI(reserva);
                            }
                        } else {
                            Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                        }
                    }
                });

    }

    private void anadirReservaUI(Reserva reserva) {

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvFecha = new TextView(this);
        TextView tvHora = new TextView(this);

        tvFecha.setText(String.valueOf(reserva.getFecha()));
        tvFecha.setTextSize(12);
        tvHora.setText(reserva.getHora());
        tvHora.setTextSize(25);

        linearLayout.addView(tvFecha);
        linearLayout.addView(tvHora);

        linearLayoutPrincipal.addView(linearLayout);

    }


}