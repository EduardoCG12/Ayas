package com.dosdeemetres.ayashome;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaReservaFragment extends Fragment{


    private RecyclerView recyclerView;
    private ArrayList<Reserva> listaReservas;

    public ListaReservaFragment() {}

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




/*       for(int i = 0; i < 50; i++)
        {
            Reserva a1 = new Reserva();

            a1.setId_Reserva(i+1);
            a1.setUsuario("Usuario " + i);
            a1.setHora("12:0" + i);
            a1.setFecha("27/"+i+"/2020");
            a1.setTipo_Reserva("Masaje");

            listaReservas.add(a1);
        }*/

        listaReservas = new ArrayList<>();
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.listaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        // POR DEFECTO CARGAMOS LOS SERVICIOS
        if (MainActivity.acct.getEmail().equals("developer.ayashome@gmail.com")){
            //hacemos la consulta
            db.collection("Reservas")
                    .document("reservasCorreos")
                    .collection("Servicios")
                    .document("serviciosCorreo")
                    .collection(MainActivity.acct.getEmail())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                //hemos creado un constructor con el objeto tipo QueryDocumentSnapshot
                                Reserva reserva = new Reserva(document);
                                reserva.id_firestore = document.getId();
                                // Log.w(Values.LOG_TAG, reserva.toString());
                                listaReservas.add(reserva);
                                // Log.e(Values.LOG_TAG, String.valueOf((listaReservas.size())));
                            }
                            recyclerView.setAdapter(new ReservaAdapter(listaReservas));
                        } else {
                            Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                        }
                }
            });
        }

        else {
            //hacemos la consulta
            db.collection("Reservas")
                    .document("reservasCorreos")
                    .collection("Servicios")
                    .document("serviciosCorreo")
                    .collection(MainActivity.acct.getEmail())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    //hemos creado un constructor con el objeto tipo QueryDocumentSnapshot
                                    Reserva reserva = new Reserva(document);
                                    reserva.id_firestore = document.getId();
                                    Log.w(Values.LOG_TAG, reserva.toString());
                                    listaReservas.add(reserva);
                                    Log.e(Values.LOG_TAG, String.valueOf((listaReservas.size())));
                                }
                                recyclerView.setAdapter(new ReservaAdapter(listaReservas));
                            } else {
                                Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                            }
                        }
                    });
        }



        Log.e(Values.LOG_TAG, String.valueOf((listaReservas.size())));

        /*// Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.listaRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ReservaAdapter(listaReservas, reservaListener));
        }*/
        return view;
    }



}