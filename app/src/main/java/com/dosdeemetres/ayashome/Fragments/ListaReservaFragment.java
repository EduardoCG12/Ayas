package com.dosdeemetres.ayashome.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.Clases.Values;
import com.dosdeemetres.ayashome.Fragments.Adapter.MyReservaRecyclerViewAdapter;
import com.dosdeemetres.ayashome.Fragments.Adapter.ReservaAdapter;
import com.dosdeemetres.ayashome.MainActivity;
import com.dosdeemetres.ayashome.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 */
public class ListaReservaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Reserva> listaReservas;

    public ListaReservaFragment() {
    }

    public static ListaReservaFragment newInstance(int columnCount) {
        ListaReservaFragment fragment = new ListaReservaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_reserva_list, container, false);


        listaReservas = new ArrayList<>();

        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //hacemos la consulta
        db.collection("Reservas")
                .document("reservasCorreos").collection("developer.ayashome@gmail.com")
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

                                Log.w(Values.LOG_TAG, document.getId() + " => " + reserva.getHora());
                                listaReservas.add(reserva);

                                for (int i=0; i < listaReservas.size(); i++){
                                    Log.w(Values.LOG_TAG, listaReservas.get(i).toString());
                                }
                            }
                        } else {
                            Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                        }
                    }
                });


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ReservaAdapter(listaReservas));
        }
        return view;
    }
}