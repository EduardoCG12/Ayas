package com.dosdeemetres.ayashome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class BotonesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "botones";

    // TODO: Rename and change types of parameters
    private ArrayList<String> listaBotones;

    String desc;
    String url;
    String precio;
    String opcion;



    public BotonesFragment() {
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
    public static BotonesFragment newInstance(ArrayList<String> texto) {
        BotonesFragment fragment = new BotonesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,texto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            listaBotones = getArguments().getStringArrayList(ARG_PARAM1);


        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_botones, container, false);

        LinearLayout linearPrincipal =  view.findViewById(R.id.linearprincipalBotones);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );

        for(int i = 0; i < listaBotones.size(); i++){
            Button button = new Button(this.getContext());
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText( listaBotones.get(i));
            //Añadimos el botón a la botonera

            button.setOnClickListener(new ButtonsOnClickListener(this));

            linearPrincipal.addView(button);
        }
        /*TextView tvFragment = view.findViewById(R.id.tvFragment);
        tvFragment.setText(textoActivity);*/
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* view.findViewById(R.id.).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


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
        });*/
    }

     class ButtonsOnClickListener implements View.OnClickListener
    {

        public ButtonsOnClickListener(BotonesFragment botonesFragment) {
        }


        @Override
        public void onClick(View v) {
            Button but = (Button) v;
            if(but.getText().equals(getResources().getString(R.string.Estetica1))){

                Bundle bundle = new Bundle();
                desc = getResources().getString(R.string.descripcionFacial);
                url = "https://ia801506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
                precio =  getResources().getString(R.string.DepilacionFacial);
                opcion = getResources().getString(R.string.Estetica1);
                bundle.putString("descripcion",desc);
                bundle.putString("img", url);
                bundle.putString("precio",precio);
                bundle.putString("opcion",opcion);


                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }

            else if (but.getText().equals(getResources().getString(R.string.Estetica2))){

                Bundle bundle = new Bundle();
                desc = getResources().getString(R.string.descripcionManicura);
                url = "https://ia801506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
                precio =  getResources().getString(R.string.PrecioManicura);
                bundle.putString("descripcion",desc);
                bundle.putString("img", url);
                bundle.putString("precio",precio);

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }

            else if (but.getText().equals(getResources().getString(R.string.Estetica3))){

                Bundle bundle = new Bundle();
                desc = getResources().getString(R.string.descripcionPedicura);
                url = "https://ia801506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
                precio =  getResources().getString(R.string.PrecioPedicura);
                bundle.putString("descripcion",desc);
                bundle.putString("img", url);
                bundle.putString("precio",precio);

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }

            else if(but.getText().equals(getResources().getString(R.string.Estetica4))){

                Bundle bundle = new Bundle();
                desc = getResources().getString(R.string.DescripcionDepilacion);
                url = "https://ia801506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
                precio =  getResources().getString(R.string.DepilacionCorporal);
                bundle.putString("descripcion",desc);
                bundle.putString("img", url);
                bundle.putString("precio",precio);

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }

            else{
                Bundle bundle = new Bundle();
                desc = getResources().getString(R.string.DescripcionDepilacion);
                url = "https://ia801506.us.archive.org/29/items/mainlogo_202010/mainlogo.png";
                precio =  getResources().getString(R.string.DepilacionFacial);
                bundle.putString("descripcion",desc);
                bundle.putString("img", url);
                bundle.putString("precio",precio);

                Fragment fragment = new TextoFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor
                        , fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }


        }
    };
}