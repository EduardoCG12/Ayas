package com.dosdeemetres.ayashome.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class ListaReservaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Reserva> listaReservas;

    public ListaReservaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
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

        // enganchamos el recyclerview
        recyclerView = view.findViewById(R.id.listaRecyclerView);

        listaReservas = new ArrayList<>();

        // Cargamos los datos de la DB


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyReservaRecyclerViewAdapter(listaReservas));
        }
        return view;
    }
}