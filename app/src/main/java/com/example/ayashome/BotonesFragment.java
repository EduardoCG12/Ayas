package com.example.ayashome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class BotonesFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_botones, container, false);
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