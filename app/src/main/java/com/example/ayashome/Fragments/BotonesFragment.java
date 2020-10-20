package com.example.ayashome.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ayashome.Clases.AdaptadorServicios;
import com.example.ayashome.Clases.Servicio;
import com.example.ayashome.R;

import java.util.ArrayList;
import java.util.List;

public class BotonesFragment extends Fragment {

    ScrollView scrollViewServicio;
    List<Servicio> listaServicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_botones, container, false);

        scrollViewServicio = view.findViewById(R.id.scrollViewServicio);

        listaServicio = new ArrayList<>();
        listaServicio.add(new Servicio("Higiene Facial", "Te ofrecemos una amplia gama de tratamientos faciales para que tu cara recupere la luz y el brillo natural", "https://ia601506.us.archive.org/29/items/mainlogo_202010/mainlogo.png"));
        listaServicio.add(new Servicio("Manicura", "Aya´s House cuenta con un completo servicio de manicura", "https://ia601506.us.archive.org/29/items/mainlogo_202010/mainlogo.png"));
        listaServicio.add(new Servicio("Pedicura", "Además de realizar un tratamiento higiénico, aplicamos la evolución de las técnicas y productos de cosmética para reparar las imperfecciones y los colores de las uñas naturales.", "https://ia601506.us.archive.org/29/items/mainlogo_202010/mainlogo.png"));
        listaServicio.add(new Servicio("Depilacion", "Somos especialistas en depilación mediante cera templada, más indolora y que retrasa el crecimiento del vello hasta 20 días.", "https://ia601506.us.archive.org/29/items/mainlogo_202010/mainlogo.png"));

        /*AdaptadorServicios adaptadorServicios = new AdaptadorServicios(){
            getActivity().getApplicationContext(),
            R.id.servicio_item,
            listaServicio
        };

        scrollViewServicio.set*/

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
                //FragmentManager fragmentManager = getFragmentManager();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.ThirdFragment, mFeedFragment);
                //fragmentTransaction.addToBackStack(null);
                //fragmentTransaction.commit();


            }
        });
        view.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
                //texto.getBaseContext(getString(R.string.texto2));

            }
        });
        view.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
        view.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
        view.findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
        view.findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
        view.findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
        view.findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BotonesFragment.this)
                        .navigate(R.id.action_BotonesFragment_to_TextoFragment);
            }
        });
    }
}