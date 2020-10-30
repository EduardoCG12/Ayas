package com.dosdeemetres.ayashome;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HabitacionesHostalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabitacionesHostalFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String habitacion;

    private ImageView habitacion101;
    private ImageView habitacion102;
    private ImageView habitacion103;


    public HabitacionesHostalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HabitacionesHostalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HabitacionesHostalFragment newInstance(String param1, String param2) {
        HabitacionesHostalFragment fragment = new HabitacionesHostalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_habitacion_hostal, container, false);

        habitacion101 = view.findViewById(R.id.parejaPos1);
        habitacion102 = view.findViewById(R.id.parejaPos2);
        habitacion103 = view.findViewById(R.id.parejaPos3);

        habitacion101.setOnClickListener(this);
        habitacion102.setOnClickListener(this);
        habitacion103.setOnClickListener(this);

        ImageView tvTfn = view.findViewById(R.id.tvTelefono);

        tvTfn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parejaPos1:

            case R.id.parejaPos2:

            case R.id.parejaPos3:
                if(MainActivity.acct == null) {
                    Snackbar.make(getView(), R.string.reservarSinCorreo,
                            Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0))
                            .show();
            }
            else{
                    siguientepantalla(v.getId());

            }
                break;

            case R.id.tvTelefono:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.telefono)));
                System.out.println(getString(R.string.telefono));
                startActivity(intent);
                break;

        }
    }

    public void siguientepantalla(final Integer id){
        if(id.equals(R.id.parejaPos1)){
             habitacion = "101";
        }
        else if(id.equals(R.id.parejaPos2)){
            habitacion = "102";
        }
        else{
            habitacion = "103";
        }
        Bundle bundle = new Bundle();
        bundle.putString("habitacion",habitacion);
        Fragment fragment = new HotelFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor
                , fragment);
        fragmentTransaction.addToBackStack(null);
        fragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}