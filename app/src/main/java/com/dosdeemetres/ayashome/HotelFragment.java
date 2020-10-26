package com.dosdeemetres.ayashome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment implements View.OnClickListener{

    private EditText usuario;
    private EditText reservaFechaEntrada;
    private EditText etHora;
    private EditText etTipoReserva;
    private EditText reservaFechaSalida;
    private RadioGroup rdgOpciones;
    private RadioButton rdSinComida;
    private RadioButton rdConComida;
    private Button butGuardar;
    public final Calendar c = Calendar.getInstance();
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private boolean entrada = true;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
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
        View view =  inflater.inflate(R.layout.fragment_hotel, container, false);
        usuario = view.findViewById(R.id.etReservaHotel);
        reservaFechaEntrada = view.findViewById(R.id.etdFechaReservaHotel);
        etHora = view.findViewById(R.id.etHoraHotel);
        etTipoReserva = view.findViewById(R.id.etTipoReservaHotel);
        reservaFechaSalida = view.findViewById(R.id.etdFechaSalidaReservaHote);
        rdgOpciones = view.findViewById(R.id.grupoRg);
        rdSinComida = view.findViewById(R.id.sinComida);
        rdConComida = view.findViewById(R.id.conComida);

        reservaFechaEntrada.setOnClickListener(this);
        reservaFechaSalida.setOnClickListener(this);
        etHora.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.etdFechaReservaHotel :


                Log.d(Values.LOG_TAG, true +"");

            case R.id.etdFechaSalidaReservaHote:

                showDatePickerDialog(v);
                entrada = false;
                Log.d(Values.LOG_TAG, false +"");
                break;
            case R.id.etHoraHotel:
                obtenerHora();
                break;
        }
    }

    private void showDatePickerDialog(final View v) {
        final DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                c.set(year, month, day);
                int fechaSeleccionada = c.get(Calendar.DAY_OF_WEEK);
                boolean esLunes = (fechaSeleccionada == Calendar.MONDAY);
                if (esLunes) {
                    Snackbar.make(getView(), "Error: Los lunes no estamos dispnibles",
                            Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0))
                            .show();
                }
                else {

                    ((EditText)v).setText(selectedDate);

                }
            }
        });

        newFragment.show(this.getFragmentManager(),"datePicker");
    }



    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "A.M";
                } else {
                    AM_PM = "P.M";
                }
                //Muestro la hora con el formato deseado
                etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
}