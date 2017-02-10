/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author JuanPablo
 */
public class Cancion {
    //atributos
    int id;
    String titulo;
    String artista;
    String duracion; 
    String genero;
    int valoracion;
    long tamanio_bytes;
    String ruta;
    String id_usuario;

    //constructor por parametros
    public Cancion(int id, String titulo, String artista, String duracion, String genero, int valoracion, long tamanio_bytes, String ruta, String id_usuario) {
        
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.genero = genero;
        this.valoracion = valoracion;
        this.tamanio_bytes = tamanio_bytes;
        this.ruta = ruta;
        this.id_usuario = id_usuario;
        
    }

    //gets y sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public long getTamanio_bytes() {
        return tamanio_bytes;
    }

    public void setTamanio_bytes(long tamanio_bytes) {
        this.tamanio_bytes = tamanio_bytes;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
}
