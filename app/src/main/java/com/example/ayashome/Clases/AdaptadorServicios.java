package com.example.ayashome.Clases;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class AdaptadorServicios extends ArrayAdapter<Servicio> {
    public AdaptadorServicios(@NonNull Context context, int resource, @NonNull List<Servicio> objects) {
        super(context, resource, objects);
    }
}
