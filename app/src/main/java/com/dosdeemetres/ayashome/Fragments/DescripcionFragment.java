package com.dosdeemetres.ayashome.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.dosdeemetres.ayashome.Clases.Values;
import com.dosdeemetres.ayashome.MainActivity;
import com.dosdeemetres.ayashome.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class DescripcionFragment extends Fragment {

    private ImageView imagen;
    private String descripcion;
    private String urlImagen;
    private String precioRe;
    private String opcion;
    private Button butres;


    public DescripcionFragment() {
        // Required empty public constructor
    }

    public static DescripcionFragment newInstance(String texto, String img, String precio, String opcion) {
        DescripcionFragment fragment = new DescripcionFragment();
        Bundle args = new Bundle();
        args.putString(Values.DESCRIPCION,texto);
        args.putString(Values.URL_IMAGEN,img);
        args.putString(Values.PRECIO,precio);
        args.putString(Values.OPCION,opcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            descripcion = getArguments().getString(Values.DESCRIPCION);
            urlImagen = getArguments().getString(Values.URL_IMAGEN);
            precioRe = getArguments().getString(Values.PRECIO);
            opcion = getArguments().getString(Values.OPCION);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_descripcion, container, false);
        TextView texto = view.findViewById(R.id.TextoCambiante);
        imagen = view.findViewById(R.id.ImagenCambiante);
        TextView precio = view.findViewById(R.id.PrecioCambiante);

        texto.setText(descripcion);
        precio.setText(precioRe);

        HiloDescargarImagen hiloDescargarImagen = new HiloDescargarImagen(urlImagen);
        hiloDescargarImagen.start();


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

                if (MainActivity.acct == null) {
                    butres = view.findViewById(R.id.reservar);

                    butres.setEnabled(false);
                    Snackbar.make(getView(), R.string.reservarSinCorreo,
                            Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255, 255, 77)).setTextColor(Color.rgb(6,7,6))
                            .show();
                } else {
                    Bundle bundle = new Bundle();

                    bundle.putString("opcion", opcion);

                    Fragment fragment = new FormularioReservaFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor
                            , fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    public class HiloDescargarImagen extends Thread {
        private final String url;
        private Bitmap bitmap;

        public HiloDescargarImagen(String url) {
            this.url = url;

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            // cargamos la foto desde la URL y la guardamos en un Bitmap
            // para setearlo en la UI en el Hilo principal
            FutureTarget<Bitmap> futureTarget =
                    Glide.with(getActivity().getApplicationContext())
                            .asBitmap()
                            .load(url)
                            .submit(600, 600);
            try {
                bitmap = futureTarget.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //necesario para poder usar los elementos visuales (view) y modificarlos
            // seteamos la imagen descargada en el imageView
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imagen.setImageBitmap(bitmap);
                }
            });
        }
    }
}