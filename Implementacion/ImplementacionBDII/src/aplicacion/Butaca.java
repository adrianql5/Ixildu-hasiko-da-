/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Butaca {
    private Integer idButaca;
    private Integer idSala;
    private Integer idSesion;
    private String tipo;

    // Constructor vacío
    public Butaca() {
    }

    // Constructor completo
    public Butaca(Integer idButaca, Integer idSala, Integer idSesion, String tipo) {
        this.idButaca = idButaca;
        this.idSala = idSala;
        this.idSesion = idSesion;
        this.tipo = tipo;
    }

    // Getters
    public Integer getIdButaca() {
        return idButaca;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public String getTipo() {
        return tipo;
    }

    // Setters
    public void setIdButaca(Integer idButaca) {
        this.idButaca = idButaca;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

