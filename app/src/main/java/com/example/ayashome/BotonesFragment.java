package com.example.ayashome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

public class BotonesFragment extends Fragment {
    public EditText editText;

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

                /*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TextoFragment textoFragment = new TextoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("texto1", editText.getText.toString());
                textoFragment.setArguments(bundle);
                ft.replace(R.id.TextoFragment, textoFragment);
                ft.addToBackStack(null);
                ft.commit();*/


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