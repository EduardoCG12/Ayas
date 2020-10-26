package com.dosdeemetres.ayashome;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    @NonNull

    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //Crea una nueva instancia de DatePickerDialog y la retorna.
        DatePickerDialog dPickerDialog = new DatePickerDialog(getActivity(), listener, year, month, day);

        //Define una fecha minima.
        //Esto deshabilita fechas anteriores.
        String minDate;
        Date fechaactual = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        minDate = sdf.format(fechaactual);
        dPickerDialog.getDatePicker().setMinDate(convertDateToMillis(minDate));
        return dPickerDialog;
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
