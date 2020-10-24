package com.dosdeemetres.ayashome;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
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

    private static final String ARG_PARAM1 = "opcion";

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
    private String subTipoReserva;
    private Boolean insert = false;

    private String opcion;



    public ReservasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservasFragment newInstance(String opcion) {
        ReservasFragment fragment = new ReservasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,opcion);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            opcion = getArguments().getString(ARG_PARAM1);
        }
    }

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

       usuario = view.findViewById(R.id.etReservaHotel);
       reservaFecha =  view.findViewById(R.id.etdFechaReservaHotel);
       etHora =  view.findViewById(R.id.etHoraHotel);
       butGuardar = view.findViewById(R.id.butReservarHotel);
       etTipoReserva = view.findViewById(R.id.etTipoReservaHotel);

       etTipoReserva.setText(opcion);
     /*  if(MainActivity.acct.getEmail()!= null){
           usuario.setText(MainActivity.acct.getEmail());
       }*/
       usuario.setText("Prueba");
       usuario.setEnabled(false);
      etTipoReserva.setEnabled(false);
        view.findViewById(R.id.etdFechaReservaHotel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

       view.findViewById(R.id.etHoraHotel).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               obtenerHora();
           }
       });

        view.findViewById(R.id.butReservarHotel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiThread miThread = new MiThread();
                miThread.execute();
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


    public void Reservar() {
        String fechaString = reservaFecha.getText().toString();
        Hora = etHora.getText().toString();
        fecha = ParseFecha(fechaString);
        Usuario = usuario.getText().toString();
        subTipoReserva = etTipoReserva.getText().toString();

        //Comparamos si los campos nos estan vacios
        if (!Hora.isEmpty() && !fechaString.isEmpty() && !Usuario.isEmpty() && !subTipoReserva.isEmpty()) {
            Date fechaactual = new Date(System.currentTimeMillis());
            if(fecha.after(fechaactual)){
                Map<String, Object> updateMap = new HashMap();
                updateMap.put("usuario", Usuario);
                updateMap.put("fecha", fechaString);
                updateMap.put("hora", Hora);
                updateMap.put("tipo_reserva","tipoReserva");
                updateMap.put("subTipo_reserva", subTipoReserva);
                updateMap.put("id_reserva",123);

                //hacemos la insert
                db.collection("Reservas")
                        .document("reservasCorreos").collection("developer.ayashome@gmail.com"/*MainActivity.acct.getEmail()*/).document()
                        .set(updateMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Snackbar.make(getView(), R.string.insertCorrecta,
                                        Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(94,235,69))
                                        .show();

                                NavHostFragment.findNavController(ReservasFragment.this)
                                        .navigate(R.id.action_ReservasFragment_to_BotonesFragment);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(getView(),  R.string.insertIncorrecta,
                                        Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0))
                                        .show();
                            }
                        });

            }
            else{
                Snackbar.make(getView(),  "ERROR: La fecha seleccionada es incorrecta", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0)).show();
            }

             } else {
            Snackbar.make(getView(),  R.string.camposVacios, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0)).show();
         }
            //Timestamp myDate = new Timestamp(fecha);
            //parametros de la bbdd


    }
    @SuppressLint("StaticFieldLeak")
    public class MiThread extends AsyncTask<Object,Integer,Integer> {
        private ProgressDialog progreso;


        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(getContext());
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Procesando su reserva...");
            progreso.setCancelable(false);
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();


        }




        @Override
        protected Integer doInBackground(Object[] objects) {

            int jumpTime = 0;
            int totalProgressTime = 100;
            while(jumpTime < totalProgressTime) {

                jumpTime += 10;
                progreso.setProgress(jumpTime);
                SystemClock.sleep(300);
            }



            Reservar();
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Integer res) {
            progreso.dismiss();


        }

    }
}