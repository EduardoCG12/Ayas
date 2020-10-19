package com.example.ayashome;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Reservas {

    public int id_Reserva;
    public String usuario;
    public com.google.firebase.Timestamp fecha;
    public String hora;
    public String tipo_Reserva;


    public Reservas(QueryDocumentSnapshot document) {
        this.id_Reserva = (int) document.get("id_Reserva");
        this.usuario = document.getString("usuario");
        this.fecha = document.getTimestamp("Fecha");
        this.hora = document.getString("Hora");
        this.tipo_Reserva = document.getString("tipo_Reserva");
    }


    //Getters
    public int getId_Reserva() {
        return id_Reserva;
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
        this.id_Reserva = id_Reserva;
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