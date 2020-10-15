package com.example.ayashome;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Reservas {

    public com.google.firebase.Timestamp fecha;
    public String hora;

    public Reservas(QueryDocumentSnapshot document) {
        this.fecha = document.getTimestamp("Fecha");
        this.hora = document.getString("Hora");
    }

    public String getHora() {
        return hora;
    }


    public Timestamp getFecha() {
        return fecha;
    }
}