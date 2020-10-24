package com.dosdeemetres.ayashome;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class TextoFragment extends Fragment {
    TextView texto, precio;
    ImageView imagen;
    String descripcion;
    String urlImagen;
    String precioRe;
    String opcion;
    Button butres;
    private static final String ARG_PARAM1 = "descripcion";
    private static final String ARG_PARAM2 = "img";
    private static final String ARG_PARAM3 = "precio";
    private static final String ARG_PARAM4 = "opcion";


    // TODO: Rename and change types of parameters




    public TextoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param texto Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextoFragment newInstance(String texto, String img, String precio, String opcion) {
        TextoFragment fragment = new TextoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,texto);
        args.putString(ARG_PARAM2,img);
        args.putString(ARG_PARAM3,precio);
        args.putString(ARG_PARAM4,opcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            descripcion = getArguments().getString(ARG_PARAM1);
            urlImagen = getArguments().getString(ARG_PARAM2);
            precioRe = getArguments().getString(ARG_PARAM3);
            opcion = getArguments().getString(ARG_PARAM4);




        }
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_texto, container, false);
        texto = view.findViewById(R.id.TextoCambiante);
        imagen = view.findViewById(R.id.ImagenCambiante);
        precio = view.findViewById(R.id.PrecioCambiante);

        texto.setText(descripcion);

        Glide.with(view.getContext())
                .load(urlImagen)
                .into(imagen);

        precio.setText(precioRe);



        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton tvTfn = view.findViewById(R.id.tvTelefono);


        tvTfn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.telefono)));
                System.out.println(getString(R.string.telefono));
                startActivity(intent);
            }
        });

        view.findViewById(R.id.reservar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();

                bundle.putString("opcion", opcion);

                Fragment fragment = new ReservasFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();


              /*  if (MainActivity.acct == null) {
                    butres = view.findViewById(R.id.reservar);

                    butres.setEnabled(false);
                    Snackbar.make(getView(), R.string.reservarSinCorreo,
                            Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255, 255, 77)).setTextColor(Color.rgb(6,7,6))
                            .show();
                } else {
                    Bundle bundle = new Bundle();

                    bundle.putString("opcion", opcion);

                    Fragment fragment = new ReservasFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor
                            , fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                *//*NavHostFragment.findNavController(TextoFragment.this)
                        .navigate(R.id.action_TextoFragment_to_ReservasFragment);*//*
                }*/

            }
        });
    }
}