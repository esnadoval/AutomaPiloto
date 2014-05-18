/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.impuestobase.managedbeans;

import co.edu.uniandes.impuestobase.dao.ImpuestoBaseDAO;
import co.edu.uniandes.impuestobase.objects.ImpuestoObject;
import co.edu.uniandes.impuestobase.objects.PersonaObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author asistente
 */
@ManagedBean
@ViewScoped
public class VerImpuestoBaseBean {

    private static final long serialVersionUID = 1L;



//BASE
    private Long idImpuesto;
    private String anioGravable;
    private boolean pagado;
    private Double total = 0.0;
    //PROPIEDADES Impuesto
    private String placa;
    //CALCULOS Impuesto
    private Double base = 0.0;
    private Double descuento = 0.0;
 
    private String pagadoString;

    
    
    private ImpuestoBaseDAO dao;

    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance() 
                         .getExternalContext().getRequestParameterMap();
        String param = parameterMap.get("cod");
        dao = new ImpuestoBaseDAO();
        dao.inicializarConexion();
        ImpuestoObject i = dao.darImpuesto(Long.parseLong(param));
        dao.cerrarConexion();
        
        idImpuesto = i.getIdImpuesto();
        
        anioGravable = i.getAnioGravable();
        
        total= i.getTotal();
        
        pagado = i.isPagado();
        
        if(pagado){
            pagadoString = "Pagado";
        }else{
            pagadoString = "Sin pagar";
        }
        //Dinamicos
        placa = i.getPlaca();
        
        
        base = i.getBase();
        
        descuento = i.getDescuento();
        
        
        
        

    }

   
    public Long getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Long idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getAnioGravable() {
        return anioGravable;
    }

    public void setAnioGravable(String anioGravable) {
        this.anioGravable = anioGravable;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public String getPagadoString() {
        return pagadoString;
    }

    public void setPagadoString(String pagadoString) {
        this.pagadoString = pagadoString;
    }

    
  

}
