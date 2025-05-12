/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

/**
 *
 * @author alumnogreibd
 */
public class AbstractDAO {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;

   
    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
    protected void setFachadaAplicacion(aplicacion.FachadaAplicacion fa){
       this.fa=fa;
    }
   
    protected aplicacion.FachadaAplicacion getFachadaAplicacion(){
       return fa;
    }
}
