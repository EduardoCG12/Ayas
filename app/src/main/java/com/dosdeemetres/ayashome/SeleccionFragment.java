package com.dosdeemetres.ayashome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class SeleccionFragment extends Fragment {


    String habitacion;
    String desc;
    String url;
    String precio;
    String opcion;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_seleccion, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnEstetica).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ArrayList<String> ar_botonesEstetica = new ArrayList<String>();

                ar_botonesEstetica.add(getResources().getString(R.string.Estetica1));
                ar_botonesEstetica.add(getResources().getString(R.string.Estetica2));
                ar_botonesEstetica.add(getResources().getString(R.string.Estetica3));
                ar_botonesEstetica.add(getResources().getString(R.string.Estetica4));
                ar_botonesEstetica.add(getResources().getString(R.string.Estetica5));

                Bundle bundleBotones = new Bundle();
                bundleBotones.putSerializable("botones",ar_botonesEstetica);

                Fragment fragment = new BotonesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundleBotones);

                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


               /* NavHostFragment.findNavController(SeleccionFragment.this)
                        .navigate(R.id.action_SeleccionFragment_to_BotonesFragment);*/
            }
        });

        view.findViewById(R.id.btnHostal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HabitacionesHostalFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        view.findViewById(R.id.btnMasaje).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> ar_botonesMasaje = new ArrayList<String>();

                ar_botonesMasaje.add(getResources().getString(R.string.masaje_facial));
                ar_botonesMasaje.add(getResources().getString(R.string.masaje_corporal));
                ar_botonesMasaje.add(getResources().getString(R.string.masaje_piernas_cansadas));

                Bundle bundle = new Bundle();
                bundle.putSerializable("botones",ar_botonesMasaje);

                Fragment fragment = new BotonesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });

        view.findViewById(R.id.btnYoga).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                desc = getResources().getString(R.string.descripcion_yoga);
                url = getResources().getString(R.string.url_yoga);
                precio =  getResources().getString(R.string.precio_yoga);
                opcion = getResources().getString(R.string.yoga);

                Bundle bundle = new Bundle();
                bundle.putString(Values.DESCRIPCION, desc);
                bundle.putString(Values.URL_IMAGEN, url);
                bundle.putString(Values.PRECIO,precio);
                bundle.putString(Values.OPCION,opcion);

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
    }
}