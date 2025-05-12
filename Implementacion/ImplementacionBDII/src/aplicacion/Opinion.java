/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Opinion {
    private String idUsuario;
    private String opinion;
    private Integer puntuacion;
    private Integer puntuacionMedia;

    // Constructor
    public Opinion(String idUsuario, String opinion, Integer puntuacion, Integer puntuacionMedia) {
        this.idUsuario = idUsuario;
        this.opinion = opinion;
        this.puntuacion = puntuacion;
        this.puntuacionMedia = puntuacionMedia;
    }

    // Getters
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getOpinion() {
        return opinion;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public Integer getPuntuacionMedia() {
        return puntuacionMedia;
    }

    // Setters
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setPuntuacionMedia(Integer puntuacionMedia) {
        this.puntuacionMedia = puntuacionMedia;
    }
}

