package com.example.practicafinal;

import java.util.UUID;

public class Receta {

    private final String id;
    private final String titulo;
    private final String categoria;
    private final String tiempo;
    private final String calorias;
    private final String ingredientes;
    private final String descripcion;

    public Receta( String titulo, String categoria, String tiempo, String calorias, String ingredientes, String descripcion){

        UUID id_random = UUID.randomUUID();
        String id= id_random.toString();
        this.titulo = titulo;
        this.categoria = categoria;
        this.tiempo = tiempo;
        this.calorias = calorias;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.id = id;
    }

    public Receta( String id,String titulo, String categoria, String tiempo, String calorias, String ingredientes, String descripcion){

        this.titulo = titulo;
        this.categoria = categoria;
        this.tiempo = tiempo;
        this.calorias = calorias;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCalorias() {
        return calorias;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTiempo() {
        return tiempo;
    }

    @Override
    public String toString (){
        return getTitulo();
    }

}
