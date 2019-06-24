/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.EmpleadoLogica;
import logica.EmpleadoLogicaLocal;
import logica.IngresoLogicaLocal;
import modelo.Empleado;
import modelo.Ingreso;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
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
    @EJB
    private EmpleadoLogicaLocal empleadoLogica;

    private List<Ingreso> listaIngresos;
    private InputText txtNitEmpleado;
    private CommandButton bntRegistrar;
    private Ingreso selectedIngreso;
    private Empleado e = new Empleado();

    /**
     * Creates a new instance of IngresoVista
     */
    public IngresoVista() {
    }

    public InputText getTxtNitEmpleado() {
        return txtNitEmpleado;
    }

    public void setTxtNitEmpleado(InputText txtNitEmpleado) {
        this.txtNitEmpleado = txtNitEmpleado;
    }

    public CommandButton getBntRegistrar() {
        return bntRegistrar;
    }

    public void setBntRegistrar(CommandButton bntRegistrar) {
        this.bntRegistrar = bntRegistrar;
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

    public void registrarIngreso() {
        try {
            Ingreso nuevoIngreso = new Ingreso();
            java.util.Date fecha = new Date();
            nuevoIngreso.setFechaingreso(fecha);
            nuevoIngreso.setHoraentradaingreso(fecha);
            e = empleadoLogica.Consultarxcedula(Integer.parseInt(txtNitEmpleado.getValue().toString()));
            nuevoIngreso.setEmpleadoingreso(e);
            if (e.getEstadoempleado().equals("ACTIVO")) {
                nuevoIngreso.setAutorizadoingreso("Autorizado");
            } else {
                nuevoIngreso.setAutorizadoingreso("No Autorizado");
            }

            ingresoLogica.registrarIngreso(nuevoIngreso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se registró correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
            Logger.getLogger(IngresoVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void seleccionarIngreso(SelectEvent e){
        selectedIngreso = (Ingreso) e.getObject();
        txtNitEmpleado.setValue(selectedIngreso.getEmpleadoingreso().getCedulaempleado());
    }
    
    public void eliminarIngreso(){
        try {
            ingresoLogica.eliminarIngreso(selectedIngreso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se eliminó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
