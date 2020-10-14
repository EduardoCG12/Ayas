package com.example.ayashome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservasFragment extends Fragment {
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static FragmentManager getSupportFragmentManager;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    private EditText usuario;
    private EditText reservaFecha;
    private EditText etHora;
    private EditText etTipoReserva;
    private Button butGuardar;
    private Date fecha;
    private String Hora;
    private String Usuario;
    private String tipoReserva;

    //Accedemos a la base de datos (Firestore)
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservas, container, false);



    }



   public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       usuario = view.findViewById(R.id.etReserva);
       reservaFecha =  view.findViewById(R.id.etdFechaReserva);
       etHora =  view.findViewById(R.id.etHora);
       butGuardar = view.findViewById(R.id.butReservar);
       etTipoReserva = view.findViewById(R.id.etTipoReserva);

      // usuario.setEnabled(false);
      // etTipoReserva.setEnabled(false);
        view.findViewById(R.id.etdFechaReserva).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

       view.findViewById(R.id.etHora).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               obtenerHora();
           }
       });

        view.findViewById(R.id.butReservar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fechaString = reservaFecha.getText().toString();
                Hora = etHora.getText().toString();
                fecha = ParseFecha(fechaString);
                Usuario = usuario.getText().toString();
                tipoReserva = etTipoReserva.getText().toString();

                Log.e(Values.LOG_TAG,"Fecha Parseada : "+fecha);
                Log.e(Values.LOG_TAG,"HORA : "+Hora);
                Timestamp myDate = new Timestamp(fecha);


                Map<String, Object> updateMap = new HashMap();
                updateMap.put("Usuario",Usuario);
                updateMap.put("Fecha", myDate);
                updateMap.put("Hora", Hora);
                updateMap.put("Tipo_Reserva", tipoReserva);


                db.collection("Reservas")
                        .document(usuario.getText().toString())
                        .set(updateMap);




                NavHostFragment.findNavController(ReservasFragment.this)
                        .navigate(R.id.action_ReservasFragment_to_SecondFragment);
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                reservaFecha.setText(selectedDate);

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




    public static Date ParseFecha(String fecha)
    {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);

        }
        catch ( ParseException ex)
        {
            Log.e(Values.LOG_TAG, "Error creando fecha: " + ex.getMessage());
            System.out.println(ex);
        }
        return fechaDate;
    }
}