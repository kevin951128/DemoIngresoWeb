/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import logica.IngresoLogicaLocal;
import modelo.Ingreso;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import persistencia.IngresoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Named(value = "ingresoVista")
@RequestScoped
public class IngresoVista {
    @EJB
        
    private IngresoLogicaLocal ingresoLogica;
    
    private List<Ingreso> listaIngresos;
    private Date txtNitContratista;
    private InputText txtNombreContratista;
    private SelectOneMenu cmbEstadoContratista;
    private CommandButton bntRegistrar;
    private Ingreso selectedIngreso;

    /**
     * Creates a new instance of IngresoVista
     */
    public IngresoVista() {    
    }

    public List<Ingreso> getListaIngresos() {
        listaIngresos = ingresoLogica.consultarIngresos();
        return listaIngresos;
    }

    public void setListaIngresos(List<Ingreso> listaIngresos) {
        this.listaIngresos = listaIngresos;
    }

    public Ingreso getSelectedIngreso() {
        return selectedIngreso;
    }

    public void setSelectedIngreso(Ingreso selectedIngreso) {
        this.selectedIngreso = selectedIngreso;
    }
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
