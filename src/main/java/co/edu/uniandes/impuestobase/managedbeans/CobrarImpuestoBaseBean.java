/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.impuestobase.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author asistente
 */
@ManagedBean

public class CobrarImpuestoBaseBean {

    private static final long serialVersionUID = 1L;

    private String name;
    private String mostrar;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void darMiNombre(){
        mostrar = name;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }
    
    
}
