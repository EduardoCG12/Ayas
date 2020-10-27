package com.dosdeemetres.ayashome.Fragments;

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
import com.dosdeemetres.ayashome.R;

import java.util.ArrayList;

public class SeleccionOpcionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = Values.BOTONES;
    private ArrayList<String> listaBotones;

    private String descripcion;
    private String urlImagen;
    private String precio;
    private String opcionEscogida;

    public SeleccionOpcionFragment() {
        // Required empty public constructor
    }

    public static SeleccionOpcionFragment newInstance(ArrayList<String> texto) {
        SeleccionOpcionFragment fragment = new SeleccionOpcionFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seleccion_opcion, container, false);

        LinearLayout linearPrincipal =  view.findViewById(R.id.linearprincipalBotones);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );

        for(int i = 0; i < listaBotones.size(); i++){
            Button button = new Button(this.getContext());
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText( listaBotones.get(i));
            // Le añadimos un listener
            button.setOnClickListener(new ButtonsOnClickListener());
            
            //Añadimos el botón a la botonera
            linearPrincipal.addView(button);
        }
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

     class ButtonsOnClickListener implements View.OnClickListener
    {

        public ButtonsOnClickListener() {
            // Constructor vacio requerido
        }


        @Override
        public void onClick(View v) {
            Button but = (Button) v;

            // Comprobamos cual es el texto del boton y le damos las propiedades correspondientes
            // para pasarle al siguiente fragment

            // ESTETICA
            if(but.getText().equals(getResources().getString(R.string.estetica_higiene_facial))){
                descripcion = getResources().getString(R.string.descripcionFacial);
                urlImagen = getResources().getString(R.string.url_estetica_higiene_facial);
                precio =  getResources().getString(R.string.PrecioDepilacionFacial);
                opcionEscogida = getResources().getString(R.string.estetica_higiene_facial);
            }

            else if (but.getText().equals(getResources().getString(R.string.estetica_manicura))){
                descripcion = getResources().getString(R.string.descripcionManicura);
                urlImagen = getResources().getString(R.string.url_estetica_manicura);
                precio =  getResources().getString(R.string.PrecioManicura);
                opcionEscogida = getResources().getString(R.string.estetica_manicura);
            }

            else if (but.getText().equals(getResources().getString(R.string.estetica_pedicura))){
                descripcion = getResources().getString(R.string.descripcionPedicura);
                urlImagen = getResources().getString(R.string.url_estetica_pedicura);
                precio =  getResources().getString(R.string.PrecioPedicura);
                opcionEscogida = getResources().getString(R.string.estetica_pedicura);
            }

            else if(but.getText().equals(getResources().getString(R.string.estetica_depilacion_corporal))){
                descripcion = getResources().getString(R.string.DescripcionDepilacion);
                urlImagen = getResources().getString(R.string.url_estetica_depilacion_corporal);
                precio =  getResources().getString(R.string.PrecioDepilacionCorporal);
                opcionEscogida = getResources().getString(R.string.estetica_depilacion_corporal);
            }

            else if(but.getText().equals(getResources().getString(R.string.estetica_depilacion_facial))){
                descripcion = getResources().getString(R.string.DescripcionDepilacion);
                urlImagen = getResources().getString(R.string.url_estetica_depilacion_facial);
                precio =  getResources().getString(R.string.PrecioDepilacionFacial);
                opcionEscogida = getResources().getString(R.string.estetica_depilacion_facial);
            }

            // MASAJES

            else if(but.getText().equals(getResources().getString(R.string.masaje_facial))){
                descripcion = getResources().getString(R.string.descripcion_masaje);
                urlImagen = getResources().getString(R.string.url_masaje_facial);
                precio =  getResources().getString(R.string.precioMasajeFacial);
                opcionEscogida = getResources().getString(R.string.masaje_facial);
            }

            else if(but.getText().equals(getResources().getString(R.string.masaje_corporal))){
                descripcion = getResources().getString(R.string.descripcion_masaje);
                urlImagen = getResources().getString(R.string.url_masaje_corporal);
                precio =  getResources().getString(R.string.precioMasajeCorporal);
                opcionEscogida = getResources().getString(R.string.masaje_corporal);
            }

            else if(but.getText().equals(getResources().getString(R.string.masaje_piernas_cansadas))){
                descripcion = getResources().getString(R.string.descripcion_masaje);
                urlImagen = getResources().getString(R.string.url_masaje_piernas_cansadas);
                precio =  getResources().getString(R.string.precioMasajePiernas);
                opcionEscogida = getResources().getString(R.string.masaje_piernas_cansadas);
            }

            // PELUQUERIA

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_cortes))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_cortes);
                urlImagen = getResources().getString(R.string.url_peluqueria_cortes);
                precio =  getResources().getString(R.string.precio_peluqueria_cortes);
                opcionEscogida = getResources().getString(R.string.peluqueria_cortes);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_mechas))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_mechas);
                urlImagen = getResources().getString(R.string.url_peluqueria_mechas);
                precio =  getResources().getString(R.string.precio_peluqueria_mechas);
                opcionEscogida = getResources().getString(R.string.peluqueria_mechas);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_marcados))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_marcados);
                urlImagen = getResources().getString(R.string.url_peluqueria_marcados);
                precio =  getResources().getString(R.string.precio_peluqueria_marcados);
                opcionEscogida = getResources().getString(R.string.peluqueria_marcados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_recogidos_trenzados))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_recogidos);
                urlImagen = getResources().getString(R.string.url_peluqueria_recogidos_trenzados);
                precio =  getResources().getString(R.string.precio_peluqueria_recogidos);
                opcionEscogida = getResources().getString(R.string.peluqueria_recogidos_trenzados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_peinados))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_peinados);
                urlImagen = getResources().getString(R.string.url_peluqueria_peinados);
                precio =  getResources().getString(R.string.precion_peluqueria_peinados);
                opcionEscogida = getResources().getString(R.string.peluqueria_peinados);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_color))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_color);
                urlImagen = getResources().getString(R.string.url_peluqueria_color);
                precio =  getResources().getString(R.string.precio_peluqueria_color);
                opcionEscogida = getResources().getString(R.string.peluqueria_color);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_color_amoniaco))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_color_amoniaco);
                urlImagen = getResources().getString(R.string.url_peluqueria_color_amoniaco);
                precio =  getResources().getString(R.string.precio_peluqueria_color_amoniaco);
                opcionEscogida = getResources().getString(R.string.peluqueria_color_amoniaco);
            }

            else if(but.getText().equals(getResources().getString(R.string.peluqueria_decoloraciones))){
                descripcion = getResources().getString(R.string.descripcion_peluqueria_decoloraciones);
                urlImagen = getResources().getString(R.string.url_peluqueria_decoloraciones);
                precio =  getResources().getString(R.string.precio_peluqueria_decoloraciones);
                opcionEscogida = getResources().getString(R.string.peluqueria_decoloraciones);
            }



            // CREACION DEL FRAGMENT
            Bundle bundle = new Bundle();
            bundle.putString(Values.DESCRIPCION, descripcion);
            bundle.putString(Values.URL_IMAGEN, urlImagen);
            bundle.putString(Values.PRECIO,precio);
            bundle.putString(Values.OPCION,opcionEscogida);

            Fragment fragment = new DescripcionFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, fragment);
            fragmentTransaction.addToBackStack(null);
            fragment.setArguments(bundle);
            fragmentTransaction.commit();

        }
    }
}