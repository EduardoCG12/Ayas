package com.example.ayashome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class SeleccionFragment extends Fragment {

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


    }
}