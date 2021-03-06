package com.dosdeemetres.ayashome.Clases;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Reserva {

    public int id_reserva;
    public String usuario;
    public com.google.firebase.Timestamp fecha;
    public String hora;
    public String tipo_Reserva;


    public Reserva(QueryDocumentSnapshot document) {
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


}