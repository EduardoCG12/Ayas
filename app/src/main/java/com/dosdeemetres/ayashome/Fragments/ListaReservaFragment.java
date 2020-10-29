package com.dosdeemetres.ayashome.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dosdeemetres.ayashome.Clases.OnReservaInteractionListener;
import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.Clases.Values;
import com.dosdeemetres.ayashome.Fragments.Adapter.ReservaAdapter;
import com.dosdeemetres.ayashome.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaReservaFragment extends Fragment implements OnReservaInteractionListener{

    private OnReservaInteractionListener reservaListener;
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

        listaReservas = new ArrayList<>();

        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

 /*       //hacemos la consulta
        db.collection("Reservas")
                .document("reservasCorreos")
                .collection("developer.ayashome@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                //hemos creado un constructor con el objeto tipo QueryDocumentSnapshot
                                Reserva reserva = new Reserva(document);
                                Log.w(Values.LOG_TAG, reserva.toString());
                                listaReservas.add(reserva);
                                Log.e(Values.LOG_TAG, String.valueOf((listaReservas.size())));
                            }
                        } else {
                            Log.e("ERROR FIRESTORE", "No se han podido obtener los datos");
                        }
                    }
                });*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_reserva_list, container, false);




       for(int i = 0; i < 50; i++)
        {
            Reserva a1 = new Reserva();

            a1.setId_Reserva(i+1);
            a1.setUsuario("Usuario " + i);
            a1.setHora("12:0" + i);
            a1.setFecha("27/"+i+"/2020");
            a1.setTipo_Reserva("Masaje");

            listaReservas.add(a1);
        }



        Log.e(Values.LOG_TAG, String.valueOf((listaReservas.size())));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.listaRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ReservaAdapter(listaReservas, reservaListener));
        }
        return view;
    }

    public void onReservaEliminada(Reserva reserva) {
        // cuando se hace click en un elemento del recyclerview
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity().getApplicationContext());
        // Add the buttons
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                // eliminar registro de DB
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        builder.setTitle("Eliminar Reserva");
        builder.setMessage("¿Desea eliminar definitivamente la reserva?");
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}