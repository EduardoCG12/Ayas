package com.example.ayashome.Clases;


public class Servicio {
    private String titulo;
    private String descripcion;

    private int numeroOpciones;
    private String urlFoto;

    public Servicio() { }

    public Servicio(String titulo, String descripcion, String urlFoto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
