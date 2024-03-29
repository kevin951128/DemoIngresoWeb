/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.CargoLogicaLocal;
import logica.EmpleadoLogicaLocal;
import modelo.Empleado;
import modelo.Cargo;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
import persistencia.CargoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Named(value = "empleadoVista")
@RequestScoped

public class EmpleadoVista {

    @EJB
    private EmpleadoLogicaLocal empleadoLogica;
    @EJB
    private CargoLogicaLocal cargoLogica;
    
    private List<Empleado> listaEmpleado;
    private InputText txtCedulaEmpleado;
    private InputText txtNombreEmpleado;
    private InputText txtApellidoEmpleado;
    private InputText txtTelefonoEmpleado;
    private SelectOneMenu cmbEstadoEmpleado;
    private SelectOneMenu cmbCodigoCargo;
    private CommandButton bntRegistrar;
    private Cargo c = new Cargo();
    private Empleado selectedEmpleado;

    /**
     * Creates a new instance of EmpleadoVista
     */
    public EmpleadoVista() {
    }

    public List<Empleado> getListaEmpleado() {
        listaEmpleado = empleadoLogica.ConsultarEmpleados();
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public InputText getTxtCedulaEmpleado() {
        return txtCedulaEmpleado;
    }

    public void setTxtCedulaEmpleado(InputText txtCedulaEmpleado) {
        this.txtCedulaEmpleado = txtCedulaEmpleado;
    }

    public InputText getTxtNombreEmpleado() {
        return txtNombreEmpleado;
    }

    public void setTxtNombreEmpleado(InputText txtNombreEmpleado) {
        this.txtNombreEmpleado = txtNombreEmpleado;
    }

    public InputText getTxtApellidoEmpleado() {
        return txtApellidoEmpleado;
    }

    public void setTxtApellidoEmpleado(InputText txtApellidoEmpleado) {
        this.txtApellidoEmpleado = txtApellidoEmpleado;
    }

    public InputText getTxtTelefonoEmpleado() {
        return txtTelefonoEmpleado;
    }

    public void setTxtTelefonoEmpleado(InputText txtTelefonoEmpleado) {
        this.txtTelefonoEmpleado = txtTelefonoEmpleado;
    }

    public SelectOneMenu getCmbEstadoEmpleado() {
        return cmbEstadoEmpleado;
    }

    public void setCmbEstadoEmpleado(SelectOneMenu cmbEstadoEmpleado) {
        this.cmbEstadoEmpleado = cmbEstadoEmpleado;
    }

    public SelectOneMenu getCmbCodigoCargo() {
        return cmbCodigoCargo;
    }

    public void setCmbCodigoCargo(SelectOneMenu cmbCodigoCargo) {
        this.cmbCodigoCargo = cmbCodigoCargo;
    }

    public CommandButton getBntRegistrar() {
        return bntRegistrar;
    }

    public void setBntRegistrar(CommandButton bntRegistrar) {
        this.bntRegistrar = bntRegistrar;
    }

    public Empleado getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public void setSelectedEmpleado(Empleado selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }

    
    public void registrarEmpleado() {
        try {
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setCedulaempleado(Long.parseLong(txtCedulaEmpleado.getValue().toString()));
            nuevoEmpleado.setNombreempleado(txtNombreEmpleado.getValue().toString());
            nuevoEmpleado.setApellidoempleado(txtApellidoEmpleado.getValue().toString());
            nuevoEmpleado.setTelefonoempleado(txtTelefonoEmpleado.getValue().toString());
            c = cargoLogica.consultarxCodigo(Integer.parseInt(cmbCodigoCargo.getValue().toString()));
            nuevoEmpleado.setCodigocargo(c);
            nuevoEmpleado.setEstadoempleado(cmbEstadoEmpleado.getValue().toString());

            empleadoLogica.RegistrarEmpleado(nuevoEmpleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se registró correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(EmpleadoVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void seleccionarEmpleado(SelectEvent e) {
        selectedEmpleado = (Empleado) e.getObject();
        txtCedulaEmpleado.setValue(selectedEmpleado.getCedulaempleado());
        txtNombreEmpleado.setValue(selectedEmpleado.getNombreempleado());
        txtApellidoEmpleado.setValue(selectedEmpleado.getApellidoempleado());
        txtTelefonoEmpleado.setValue(selectedEmpleado.getTelefonoempleado());
        cmbEstadoEmpleado.setValue(selectedEmpleado.getEstadoempleado());
        cmbCodigoCargo.setValue(selectedEmpleado.getCodigocargo());
    }

    public void modificarEmpleado() {
        try {
            Empleado nuevoEmpleado = selectedEmpleado;
            nuevoEmpleado.setCedulaempleado(Long.parseLong(txtCedulaEmpleado.getValue().toString()));
            nuevoEmpleado.setNombreempleado(txtNombreEmpleado.getValue().toString());
            nuevoEmpleado.setApellidoempleado(txtApellidoEmpleado.getValue().toString());
            nuevoEmpleado.setTelefonoempleado(txtTelefonoEmpleado.getValue().toString());
            c = cargoLogica.consultarxCodigo(Integer.parseInt(cmbCodigoCargo.getValue().toString()));
            nuevoEmpleado.setCodigocargo(c);
            nuevoEmpleado.setEstadoempleado(cmbEstadoEmpleado.getValue().toString());

            empleadoLogica.modificarEmpleado(nuevoEmpleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se modificó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarEmpleado() {
        try {
            empleadoLogica.eliminarEmpleado(selectedEmpleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se eliminó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(EmpleadoVista.class.getName()).log(Level.SEVERE, null, ex);
        }
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
