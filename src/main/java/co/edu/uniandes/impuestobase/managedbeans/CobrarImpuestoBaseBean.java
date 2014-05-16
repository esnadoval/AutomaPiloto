/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.impuestobase.managedbeans;

import co.edu.uniandes.impuestobase.objects.ImpuestoObject;
import co.edu.uniandes.impuestobase.objects.PersonaObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
     
     
     private  ImpuestoObject impuesto;
     private PersonaObject persona;
//BASE
    private Long idImpuesto;
    private String anioGravable;
    private boolean pagado;
    private Double total= 0.0;
    //PROPIEDADES
    private String placa;
    //CALCULOS
    private Double base = 0.0;
    private Double descuento= 0.0;
    //BASE
    private String idIPersona;

    //PROPIEDADES
    private String nombre;
    private String apellido;

    @PostConstruct
    public void init() {
        Random r = new Random();
        idImpuesto = 0 +r.nextLong() * 999999;
        anios = new ArrayList<String>();
        anios.add("2013");
        anios.add("2014");
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
        anioGravable = "2014";
        obtenerPersonas();
        impuesto = new ImpuestoObject();
        persona = new PersonaObject();
    }

    public void obtenerPersonas(){
        cedulas = new ArrayList<String>();
        cedulas.add("123123");
        cedulas.add("9809080");
        cedulas.add("45654");
    }
    public void obtenerPersona(){
        
    }
    public void calcularImpuesto() {
        total = (5 + base - descuento);

    }
      public void pagarImpuesto() {
        total = (5 + base - descuento);

    }
    public void actualizarPersonas() {
        total = (5 + base - descuento);

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

}
