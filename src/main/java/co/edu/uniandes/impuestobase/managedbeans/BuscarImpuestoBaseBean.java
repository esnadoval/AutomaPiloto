/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.impuestobase.managedbeans;

import co.edu.uniandes.impuestobase.dao.ImpuestoBaseDAO;
import co.edu.uniandes.impuestobase.objects.ImpuestoObject;
import co.edu.uniandes.impuestobase.objects.PersonaObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author asistente
 */
@ManagedBean
@ViewScoped
public class BuscarImpuestoBaseBean {

    private static final long serialVersionUID = 1L;

    private List<String> anios;
  

//BASE

    private String anioGravable;
    
    private String idIPersona;

    private String tableOut;

    private ImpuestoBaseDAO dao;

    @PostConstruct
    public void init() {
        dao = new ImpuestoBaseDAO();
        dao.inicializarConexion();
        dao.crearTablas();
        dao.cerrarConexion();
        
        
        anios = new ArrayList<String>();
        anios.add("2013");
        anios.add("2014");
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
        anioGravable = "2014";
      
    }

    

    public void obtenerImpuestos() {
        dao.inicializarConexion();
        ArrayList<ImpuestoObject> imp =  dao.darImpuestosUsuario(idIPersona, anioGravable);
        dao.cerrarConexion();
        tableOut = "<h2>Resultados</h2><br/><table><tr><td><b>Código Impuesto</b></td><td><b>Comprobante</b></td></tr>";
        for (ImpuestoObject impuestoObject : imp) {
            tableOut += "<tr><td>"+impuestoObject.getIdImpuesto()+"</td><td><a href=\"ver.jsf?cod="+impuestoObject.getIdImpuesto()+"\">Ver</a></td></tr>";
        }
        tableOut +="</table>";
        //Dinamicos
        
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

  
    public String getAnioGravable() {
        return anioGravable;
    }

    public void setAnioGravable(String anioGravable) {
        this.anioGravable = anioGravable;
    }

    public String getIdIPersona() {
        return idIPersona;
    }

    public void setIdIPersona(String idIPersona) {
        this.idIPersona = idIPersona;
    }

    public String getTableOut() {
        return tableOut;
    }

    public void setTableOut(String tableOut) {
        this.tableOut = tableOut;
    }

}
