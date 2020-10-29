package com.dosdeemetres.ayashome;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;

public class Reservas {

    public int id_reserva;
    public String usuario;
    public String fecha;
    public String hora;
    public String tipo_Reserva;


    public Reservas(QueryDocumentSnapshot document) {
        this.id_reserva = Integer.parseInt( document.get("id_reserva").toString());
        this.usuario = document.getString("usuario");
        this.fecha = document.getString("fecha");
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

    public String getFecha() {
        return fecha;
    }

    //Setters
    public void setId_Reserva(int id_Reserva) {
        this.id_reserva = id_Reserva;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipo_Reserva(String tipo_Reserva) {
        this.tipo_Reserva = tipo_Reserva;
    }


}