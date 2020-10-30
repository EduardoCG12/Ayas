package com.dosdeemetres.ayashome;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnHostal = view.findViewById(R.id.btnHostal);
        Button btnEstetica = view.findViewById(R.id.btnEstetica);
        Button btnMasaje = view.findViewById(R.id.btnMasaje);
        Button btnPeluqueria = view.findViewById(R.id.btnPeluqueria);
        Button btnYoga = view.findViewById(R.id.btnYoga);

        // Hilo para descargar las imagenes
        HiloDescargarImagen hiloDescargarImagen = new HiloDescargarImagen(getResources().getString(R.string.url_ESTETICA), btnEstetica);
        hiloDescargarImagen.start();

        HiloDescargarImagen hiloDescargarImagen2 = new HiloDescargarImagen(getResources().getString(R.string.url_HOSTAL), btnHostal);
        hiloDescargarImagen2.start();

        HiloDescargarImagen hiloDescargarImagen3 = new HiloDescargarImagen(getResources().getString(R.string.url_MASAJE), btnMasaje);
        hiloDescargarImagen3.start();

        HiloDescargarImagen hiloDescargarImagen4 = new HiloDescargarImagen(getResources().getString(R.string.url_PELUQUERIA), btnPeluqueria);
        hiloDescargarImagen4.start();

        HiloDescargarImagen hiloDescargarImagen5 = new HiloDescargarImagen(getResources().getString(R.string.url_YOGA), btnYoga);
        hiloDescargarImagen5.start();

        view.findViewById(R.id.btnHostal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HabitacionesHostalFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        view.findViewById(R.id.btnEstetica).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ArrayList<String> alBotones = new ArrayList<>();
                alBotones.add(getResources().getString(R.string.estetica_higiene_facial));
                alBotones.add(getResources().getString(R.string.estetica_manicura));
                alBotones.add(getResources().getString(R.string.estetica_pedicura));
                alBotones.add(getResources().getString(R.string.estetica_depilacion_corporal));
                alBotones.add(getResources().getString(R.string.estetica_depilacion_facial));

                Bundle bundleBotones = new Bundle();
                bundleBotones.putSerializable(Values.BOTONES,alBotones);

                Fragment fragment = new SeleccionOpcionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundleBotones);
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        view.findViewById(R.id.btnMasaje).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> alBotones = new ArrayList<>();
                alBotones.add(getResources().getString(R.string.masaje_facial));
                alBotones.add(getResources().getString(R.string.masaje_corporal));
                alBotones.add(getResources().getString(R.string.masaje_piernas_cansadas));

                Bundle bundleBotones = new Bundle();
                bundleBotones.putSerializable(Values.BOTONES,alBotones);

                Fragment fragment = new SeleccionOpcionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundleBotones);
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        view.findViewById(R.id.btnPeluqueria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> alBotones = new ArrayList<>();
                alBotones.add(getResources().getString(R.string.peluqueria_cortes));
                alBotones.add(getResources().getString(R.string.peluqueria_mechas));
                alBotones.add(getResources().getString(R.string.peluqueria_marcados));
                alBotones.add(getResources().getString(R.string.peluqueria_recogidos_trenzados));
                alBotones.add(getResources().getString(R.string.peluqueria_peinados));
                alBotones.add(getResources().getString(R.string.peluqueria_color));
                alBotones.add(getResources().getString(R.string.peluqueria_color_amoniaco));
                alBotones.add(getResources().getString(R.string.peluqueria_decoloraciones));

                Bundle bundleBotones = new Bundle();
                bundleBotones.putSerializable(Values.BOTONES,alBotones);

                Fragment fragment = new SeleccionOpcionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundleBotones);
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        view.findViewById(R.id.btnYoga).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Values.DESCRIPCION, getResources().getString(R.string.descripcion_yoga));
                bundle.putString(Values.URL_IMAGEN, getResources().getString(R.string.url_yoga));
                bundle.putString(Values.PRECIO, getResources().getString(R.string.precio_yoga));
                bundle.putString(Values.OPCION, getResources().getString(R.string.yoga));
                bundle.putString(Values.OPCION_PRINCIPAL, getResources().getString(R.string.yoga));

                Fragment fragment = new DescripcionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor, fragment);
                fragmentTransaction.addToBackStack(null);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
    }

    public class HiloDescargarImagen extends Thread {
        private final String url;
        private final Button btn;
        private Drawable drawable;

        public HiloDescargarImagen(String url, Button btn) {
            this.url = url;
            this.btn = btn;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            // cargamos la foto desde la URL y la guardamos en un Bitmap
            // para setearlo en la UI en el Hilo principal
            FutureTarget<Bitmap> futureTarget =
                    Glide.with(requireActivity().getApplicationContext())
                            .asBitmap()
                            .load(url)
                            .submit(600, 600);
            try {
                Bitmap bitmap = futureTarget.get();
                drawable = new BitmapDrawable(getResources(), bitmap);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            //necesario para poder usar los elementos visuales (view) y modificarlos
            // seteamos la imagen descargada en el boton
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btn.setBackground(drawable);
                }
            });
        }
    }
}