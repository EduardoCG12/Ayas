package com.dosdeemetres.ayashome.Fragments;

import android.graphics.drawable.Drawable;
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

import com.dosdeemetres.ayashome.Clases.Values;
import com.dosdeemetres.ayashome.Fragments.TextoFragment;
import com.dosdeemetres.ayashome.R;

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

        // Blucle para crear dinamicamente las opciones de los servicios
        for(int i = 0; i < listaBotones.size(); i++){
            Button button = new Button(this.getContext());
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText( listaBotones.get(i));
            button.setPadding(0,100,0,100);
            button.setTextSize(19);

            //Añadimos el botón a la botonera

            button.setOnClickListener(new ButtonsOnClickListener(this));

            linearPrincipal.addView(button);
        }

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class ButtonsOnClickListener implements View.OnClickListener
    {

        public ButtonsOnClickListener(BotonesFragment botonesFragment) {
        }


        @Override

        //comparamos que boton ha sido pulsado para pasar la informacion a TextoFragment
        public void onClick(View v) {
            Button but = (Button) v;
            if(but.getText().equals(getResources().getString(R.string.estetica_higiene_facial))){

                desc = getResources().getString(R.string.descripcionFacial);
                url = getResources().getString(R.string.url_estetica_higiene_facial);
                precio =  getResources().getString(R.string.PrecioDepilacionFacial);
                opcion = getResources().getString(R.string.estetica_higiene_facial);

            }

            else if (but.getText().equals(getResources().getString(R.string.estetica_manicura))){


                desc = getResources().getString(R.string.descripcionManicura);
                url = getResources().getString(R.string.url_estetica_manicura);
                precio =  getResources().getString(R.string.PrecioManicura);
                opcion = getResources().getString(R.string.estetica_manicura);




            }

            else if (but.getText().equals(getResources().getString(R.string.estetica_pedicura))){


                desc = getResources().getString(R.string.descripcionPedicura);
                url = getResources().getString(R.string.url_estetica_pedicura);
                precio =  getResources().getString(R.string.PrecioPedicura);
                opcion = getResources().getString(R.string.estetica_pedicura);



            }

            else if(but.getText().equals(getResources().getString(R.string.estetica_depilacion_corporal))){


                desc = getResources().getString(R.string.DescripcionDepilacion);
                url = getResources().getString(R.string.url_estetica_depilacion_corporal);
                precio =  getResources().getString(R.string.PrecioDepilacionCorporal);
                opcion = getResources().getString(R.string.estetica_depilacion_corporal);

            }



            else if (but.getText().equals(getResources().getString(R.string.estetica_depilacion_facial))){

                desc = getResources().getString(R.string.DescripcionDepilacion);
                url = getResources().getString(R.string.url_estetica_depilacion_facial);
                precio =  getResources().getString(R.string.PrecioDepilacionFacial);
                opcion = getResources().getString(R.string.estetica_depilacion_facial);
            }

            else if(but.getText().equals(getResources().getString(R.string.masaje_facial))){
                desc = getResources().getString(R.string.descripcion_masaje);
                url = getResources().getString(R.string.url_masaje_facial);
                precio =  getResources().getString(R.string.precioMasajeFacial);
                opcion = getResources().getString(R.string.masaje_facial);
            }

            else if(but.getText().equals(getResources().getString(R.string.masaje_corporal))){
                desc = getResources().getString(R.string.descripcion_masaje);
                url = getResources().getString(R.string.url_masaje_corporal);
                precio =  getResources().getString(R.string.precioMasajeCorporal);
                opcion = getResources().getString(R.string.masaje_corporal);
            }

            else if(but.getText().equals(getResources().getString(R.string.masaje_piernas_cansadas))){
                desc = getResources().getString(R.string.descripcion_masaje);
                url = getResources().getString(R.string.url_masaje_piernas_cansadas);
                precio =  getResources().getString(R.string.precioMasajePiernas);
                opcion = getResources().getString(R.string.masaje_piernas_cansadas);
            }

            // PELUQUERIA

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_cortes))){
                desc = getResources().getString(R.string.descripcion_peluqueria_cortes);
                url = getResources().getString(R.string.url_peluqueria_cortes);
                precio =  getResources().getString(R.string.precio_peluqueria_cortes);
                opcion = getResources().getString(R.string.peluqueria_cortes);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_mechas))){
                desc = getResources().getString(R.string.descripcion_peluqueria_mechas);
                url = getResources().getString(R.string.url_peluqueria_mechas);
                precio =  getResources().getString(R.string.precio_peluqueria_mechas);
                opcion = getResources().getString(R.string.peluqueria_mechas);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_marcados))){
                desc = getResources().getString(R.string.descripcion_peluqueria_marcados);
                url = getResources().getString(R.string.url_peluqueria_marcados);
                precio =  getResources().getString(R.string.precio_peluqueria_marcados);
                opcion = getResources().getString(R.string.peluqueria_marcados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_recogidos_trenzados))){
                desc = getResources().getString(R.string.descripcion_peluqueria_recogidos);
                url = getResources().getString(R.string.url_peluqueria_recogidos_trenzados);
                precio =  getResources().getString(R.string.precio_peluqueria_recogidos);
                opcion = getResources().getString(R.string.peluqueria_recogidos_trenzados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_peinados))){
                desc = getResources().getString(R.string.descripcion_peluqueria_peinados);
                url = getResources().getString(R.string.url_peluqueria_peinados);
                precio =  getResources().getString(R.string.precion_peluqueria_peinados);
                opcion = getResources().getString(R.string.peluqueria_peinados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_color))){
                desc = getResources().getString(R.string.descripcion_peluqueria_color);
                url = getResources().getString(R.string.url_peluqueria_color);
                precio =  getResources().getString(R.string.precio_peluqueria_color);
                opcion = getResources().getString(R.string.peluqueria_color);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_color_amoniaco))){
                desc = getResources().getString(R.string.descripcion_peluqueria_color_amoniaco);
                url = getResources().getString(R.string.url_peluqueria_color_amoniaco);
                precio =  getResources().getString(R.string.precio_peluqueria_color_amoniaco);
                opcion = getResources().getString(R.string.peluqueria_color_amoniaco);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_decoloraciones))){
                desc = getResources().getString(R.string.descripcion_peluqueria_decoloraciones);
                url = getResources().getString(R.string.url_peluqueria_decoloraciones);
                precio =  getResources().getString(R.string.precio_peluqueria_decoloraciones);
                opcion = getResources().getString(R.string.peluqueria_decoloraciones);
            }



            // CREACION DEL FRAGMENT
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
    };
}