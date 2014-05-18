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
public class CobrarImpuestoBaseBean {

    private static final long serialVersionUID = 1L;

    private List<String> anios;
    private List<String> cedulas;

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
    //BASE Persona
    private String idIPersona;
    private String idIPersonaSel;
    //PROPIEDADES Persona
    private String nombre;
    private String apellido;

    private ImpuestoBaseDAO dao;

    @PostConstruct
    public void init() {
    
        dao = new ImpuestoBaseDAO();
        dao.inicializarConexion();
        dao.crearTablas();
        dao.cerrarConexion();
        
        idImpuesto = 2+ (long)(Math.random() * ((99999 - 5) + 1));
        anios = new ArrayList<String>();
        anios.add("2013");
        anios.add("2014");
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
        anioGravable = "2014";
        obtenerPersonas();

    }

    public void obtenerPersonas() {
        dao.inicializarConexion();
        cedulas = dao.darCedulas();
        dao.cerrarConexion();
        if (!cedulas.isEmpty()) {
            idIPersonaSel = cedulas.get(0);
        }
    }

    public void obtenerPersona() {
        dao.inicializarConexion();
        PersonaObject p = dao.darInfoPersona(idIPersonaSel);
        dao.cerrarConexion();
        idIPersona = p.getIdIPersona();
        //Dinamicos
        nombre = p.getNombre();
        apellido = p.getApellido();
    }

    public void calcularImpuesto() {
        total = (5 + base - descuento);

    }

    public void pagarImpuesto() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            boolean val = true;
            
//Obligatorios persona
            if (idIPersona.equals("")) {
                facesContext.addMessage(null, new FacesMessage("El campo cédula es obligatorio"));
                val = false;
            }
//Opcionales
            if (nombre.equals("")) {
                facesContext.addMessage(null, new FacesMessage("El campo nombre es obligatorio"));
                val = false;
            }
            if (apellido.equals("")) {
                facesContext.addMessage(null, new FacesMessage("El campo apellido es obligatorio"));
                val = false;
            }
            
            if (idImpuesto.equals("")) {
                facesContext.addMessage(null, new FacesMessage("El campo Código es obligatorio"));
                val = false;
            }
            if (placa.equals("")) {
                facesContext.addMessage(null, new FacesMessage("El campo placa es obligatorio"));
                val = false;
            }
            
            if (!val) {
                return;
            }
            PersonaObject p = new PersonaObject();
            p.setIdIPersona(idIPersona);
            p.setNombre(nombre);
            p.setApellido(apellido);
            dao.inicializarConexion();
            if (dao.guardarPersona(p) == false) {
                dao.actualizarPersona(p);
            }
            calcularImpuesto();
            ImpuestoObject i = new ImpuestoObject();
            //obligatorios
            i.setIdImpuesto(idImpuesto);
            i.setTotal(total);
            i.setPagado(true);
            i.setAnioGravable(anioGravable);
            //atributos adicionales
            i.setPlaca(placa);
            //elementos de calculo
            i.setBase(base);
            i.setDescuento(descuento);
            
            if(!dao.guardarImpuesto(i, p.getIdIPersona())){
                facesContext.addMessage(null, new FacesMessage("Error al Pagar Impuesto."));
                return;
            }
            dao.pagarImpuesto(i.getIdImpuesto());
            dao.cerrarConexion();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/exito.jsf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarPersonas() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean val = true;
        String err = "Error al validar los campos ";
//Obligatorios persona    
        if (idIPersona.equals("")) {
            err += "Cédula ";
            val = false;
        }
//Opcionales
        if (nombre.equals("")) {
            err += "Nombre ";
            val = false;
        }
        if (apellido.equals("")) {
           err += "Apellido ";
            val = false;
        }
         if (!val) {
             facesContext.addMessage(null, new FacesMessage(err+"."));
            return;
        }
        PersonaObject p = new PersonaObject();
        p.setIdIPersona(idIPersona);
        p.setNombre(nombre);
        p.setApellido(apellido);
        dao.inicializarConexion();
        if (!dao.actualizarPersona(p)) {
            dao.guardarPersona(p);
        }
        dao.cerrarConexion();

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

    public String getIdIPersona() {
        return idIPersona;
    }

    public void setIdIPersona(String idIPersona) {
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

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public List<String> getCedulas() {
        return cedulas;
    }

    public void setCedulas(List<String> cedulas) {
        this.cedulas = cedulas;
    }

    public String getIdIPersonaSel() {
        return idIPersonaSel;
    }

    public void setIdIPersonaSel(String idIPersonaSel) {
        this.idIPersonaSel = idIPersonaSel;
    }

  

}
