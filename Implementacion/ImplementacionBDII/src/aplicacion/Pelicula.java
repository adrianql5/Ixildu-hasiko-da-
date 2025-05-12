/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pelicula {
    private String titulo;
    private LocalTime duracion;
    private String genero;
    private String sinopsis;
    private String clasificacion;
    private String idioma;
    private LocalDate fechaEstreno;
    private LocalTime duracionTrailer;

    public Pelicula(String titulo, LocalTime duracion, String genero, String sinopsis, String clasificacion,
                    String idioma, LocalDate fechaEstreno, LocalTime duracionTrailer) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.clasificacion = clasificacion;
        this.idioma = idioma;
        this.fechaEstreno = fechaEstreno;
        this.duracionTrailer = duracionTrailer;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public LocalTime getDuracionTrailer() {
        return duracionTrailer;
    }

    public void setDuracionTrailer(LocalTime duracionTrailer) {
        this.duracionTrailer = duracionTrailer;
    }
}
  
