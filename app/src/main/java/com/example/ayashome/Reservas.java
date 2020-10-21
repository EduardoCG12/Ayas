package com.example.ayashome;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Reservas {

    public int id_reserva;
    public String usuario;
    public com.google.firebase.Timestamp fecha;
    public String hora;
    public String tipo_Reserva;


    public Reservas(QueryDocumentSnapshot document) {
        this.id_reserva = Integer.parseInt( document.get("id_reserva").toString());
        this.usuario = document.getString("usuario");
        this.fecha = document.getTimestamp("fecha");
        this.hora = document.getString("hora");
        this.tipo_Reserva = document.getString("tipo_reserva");
    }


    //Getters
    public int getId_Reserva() {
        return id_reserva;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTipo_Reserva() {
        return tipo_Reserva;
    }

    public String getHora() {
        return hora;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    //Setters
    public void setId_Reserva(int id_Reserva) {
        this.id_reserva = id_Reserva;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipo_Reserva(String tipo_Reserva) {
        this.tipo_Reserva = tipo_Reserva;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
        public String toDate(long timestamp) {
        LocalDate date = Instant.ofEpochMilli(timestamp * 1000).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}