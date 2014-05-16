/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.impuestobase.objects;

/**
 *
 * @author asistente
 */
public class PersonaObject {
       //BASE
    private Long idIPersona;
 
    //PROPIEDADES
    private String nombre;
    private String apellido;

    public Long getIdIPersona() {
        return idIPersona;
    }

    public void setIdIPersona(Long idIPersona) {
        this.idIPersona = idIPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
}
