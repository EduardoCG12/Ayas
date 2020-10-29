package com.dosdeemetres.ayashome;

import com.google.firebase.firestore.QueryDocumentSnapshot;

public class ReservaHotel {

    public String usuario;
    public String fechaEntrada;
    public String fechaSalida;
    public String opcion_comida;
    public String reserva;
    public String subtipo_Reserva;
    public ReservaHotel() {}

    public ReservaHotel(String usuario, String fechaEntrada, String fechaSalida, String opcion_comida, String reserva, String subtipo_Reserva) {
        this.usuario = usuario;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.opcion_comida = opcion_comida;
        this.reserva = reserva;
        this.subtipo_Reserva = subtipo_Reserva;
    }

    public ReservaHotel(QueryDocumentSnapshot document) {
        this.usuario = document.getString("usuario");
        this.fechaEntrada = document.getString("fechaEntrada");
        this.fechaSalida = document.getString("fechaSalida");
        this.opcion_comida = document.getString("opcionComida");
        this.reserva = document.getString("reserva");
        this.subtipo_Reserva = document.getString("subTipoReserva");
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getOpcion_comida() {
        return opcion_comida;
    }

    public void setOpcion_comida(String opcion_comida) {
        this.opcion_comida = opcion_comida;
    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getSubtipo_Reserva() {
        return subtipo_Reserva;
    }

    public void setSubtipo_Reserva(String subtipo_Reserva) {
        this.subtipo_Reserva = subtipo_Reserva;
    }
}
