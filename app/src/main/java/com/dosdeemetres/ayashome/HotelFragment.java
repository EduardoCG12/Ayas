package com.dosdeemetres.ayashome;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment implements View.OnClickListener{

    private EditText usuario;
    private EditText reservaFechaEntrada;
    private EditText etTipoReserva;
    private EditText reservaFechaSalida;
    private RadioButton rdConComida;
    private Button butGuardar;
    private RadioButton rdSinComida;
    public final Calendar c = Calendar.getInstance();
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    boolean fechaMal = false;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "habitacion";


    // TODO: Rename and change types of parameters
    private String habitacionParam;


    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String habitacion) {
        HotelFragment fragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, habitacion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            habitacionParam = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hotel, container, false);
        usuario = view.findViewById(R.id.etClienteHotel);
        reservaFechaEntrada = view.findViewById(R.id.etdFechaReservaHotel);
        etTipoReserva = view.findViewById(R.id.etTipoReservaHotel);
        reservaFechaSalida = view.findViewById(R.id.etdFechaSalidaReservaHotel);
        RadioGroup rdgOpciones = view.findViewById(R.id.grupoRg);
        rdSinComida = view.findViewById(R.id.sinComida);
        rdConComida = view.findViewById(R.id.conComida);
        butGuardar = view.findViewById(R.id.butReservarHotel);

        reservaFechaEntrada.setOnClickListener(this);
        reservaFechaSalida.setOnClickListener(this);
        butGuardar.setOnClickListener(this);
        rdSinComida.setChecked(true);
        usuario.setEnabled(false);
        usuario.setText(MainActivity.acct.getEmail());

        etTipoReserva.setEnabled(false);
        etTipoReserva.setText("Habitacion "+habitacionParam);


        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.etdFechaReservaHotel:

            case R.id.etdFechaSalidaReservaHotel:
                showDatePickerDialog(v);
                break;
            case R.id.butReservarHotel:
                if(fechaMal == false){
                    MiThread miThread = new MiThread();
                    miThread.execute();
                }
                else{
                    Log.d(Values.LOG_TAG,"Var: "+fechaMal);
                    Snackbar.make(getView(), R.string.mensajeFechaSalidaErronea,
                            Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0))
                            .show();
                }

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

                // Log.d(Values.LOG_TAG, "selectedDate : "+selectedDate);
                boolean esLunes = (fechaSeleccionada == Calendar.MONDAY);



                if(v.getId() == R.id.etdFechaSalidaReservaHotel) {
                    Date date=ParseFecha(selectedDate);

                    Date fechaEntrada=ParseFecha(reservaFechaEntrada.getText().toString());

                    if (date.before(fechaEntrada) || date.equals(fechaEntrada)){
                        Snackbar.make(getView(), R.string.mensajeFechaSalidaErronea,
                                Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0))
                                .show();

                        fechaMal =true;
                    }
                    else{
                        fechaMal =false;
                    }

                }


                if (esLunes) {
                    Snackbar.make(getView(), R.string.mensajeEleccionLunes,
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


    public class MiThread extends AsyncTask<Object,Integer,Integer> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(getContext());
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage(getResources().getString(R.string.msgHilo));
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

    public void Reservar() {
        //Accedemos a la base de datos (Firestore)
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fechaStringEntrada = reservaFechaEntrada.getText().toString();
        String fechaStringSalida = reservaFechaSalida.getText().toString();
        Date fechaEntrada = ParseFecha(fechaStringEntrada);
        Date fechaSalida = ParseFecha(fechaStringSalida);
        String user = usuario.getText().toString();
        String subTipoReserva = etTipoReserva.getText().toString();

        String eleccionComida;

        if(rdConComida.isSelected()){
            eleccionComida = "Con comida";
        }
        else{
            eleccionComida = "Sin comida";
        }

        if(!fechaStringEntrada.isEmpty() && !fechaStringSalida.isEmpty() && !user.isEmpty() && !subTipoReserva.isEmpty()){
            Map<String, Object> updateMap = new HashMap();
            updateMap.put("usuario", user);
            updateMap.put("reserva", "Hostal");
            updateMap.put("subTipoReserva", subTipoReserva);
            updateMap.put("fechaEntrada", fechaEntrada);
            updateMap.put("fechaSalida", fechaSalida);
            updateMap.put("opcionComida", eleccionComida);

            db.collection("Reservas")
                    .document("reservasCorreos")
                    .collection("Hotel")
                    .document("hotelCorreo")
                    .collection(MainActivity.acct.getEmail())
                    .document()
                    .set(updateMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(getView(), R.string.insertCorrecta,
                                    Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(94,235,69))
                                    .show();

                            Fragment fragment = new MainFragment();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contenedor
                                    , fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
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

        }else{
            Snackbar.make(getView(),  R.string.camposVacios, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(255,0,0)).show();
        }


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

    private Long convertDateToMillis(String givenDateString){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long timeInMilliseconds = System.currentTimeMillis() - 1000;
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milliseconds: " + timeInMilliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }


}
