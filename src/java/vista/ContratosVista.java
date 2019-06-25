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
import logica.ContratoLogicaLocal;
import modelo.Contratista;
import modelo.Contratos;
import modelo.Empleado;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.SelectEvent;
import persistencia.ContratistaFacadeLocal;
import persistencia.ContratosFacadeLocal;
import persistencia.EmpleadoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Named(value = "contratosvista")
@RequestScoped
public class ContratosVista {

    @EJB
    private ContratoLogicaLocal contratoLogica;

    @EJB
    private ContratosFacadeLocal contratosDAO;
    @EJB
    private ContratistaFacadeLocal  contratistaDAO;
     @EJB
    private EmpleadoFacadeLocal  empleadoDAO;
    
    private List<Contratos> listaContratos;
    private InputText txtCodContratista;
    private InputText txtCodEmpleado;
    private CommandButton bntRegistrar;
    private CommandButton btnModificar;
    private CommandButton bntLimpiar;
    private CommandButton bntEliminar;
    private Contratos selectedContratos;
    public ContratosVista() {
    }

    public List<Contratos> getListaContratos() {
        listaContratos = contratosDAO.findAll();
        return listaContratos;
    }

    public void setListaContratos(List<Contratos> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public InputText getTxtCodContratista() {
        return txtCodContratista;
    }

    public void setTxtCodContratista(InputText txtCodContratista) {
        this.txtCodContratista = txtCodContratista;
    }

    public InputText getTxtCodEmpleado() {
        return txtCodEmpleado;
    }

    public void setTxtCodEmpleado(InputText txtCodEmpleado) {
        this.txtCodEmpleado = txtCodEmpleado;
    }

    public CommandButton getBntRegistrar() {
        return bntRegistrar;
    }

    public void setBntRegistrar(CommandButton bntRegistrar) {
        this.bntRegistrar = bntRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBntLimpiar() {
        return bntLimpiar;
    }

    public void setBntLimpiar(CommandButton bntLimpiar) {
        this.bntLimpiar = bntLimpiar;
    }

    public CommandButton getBntEliminar() {
        return bntEliminar;
    }

    public void setBntEliminar(CommandButton bntEliminar) {
        this.bntEliminar = bntEliminar;
    }

    public Contratos getSelectedContratos() {
        return selectedContratos;
    }

    public void setSelectedContratos(Contratos selectedContratos) {
        this.selectedContratos = selectedContratos;
    }
    public void registrarContrato() {
        try {
            Contratos nuevoContratos = new Contratos();
            java.util.Date fecha = new Date();
            Contratista contratista = contratistaDAO.findxNit(Long.parseLong(txtCodContratista.getValue().toString()));
            Empleado empleados = empleadoDAO.findxCedula(Integer.parseInt(txtCodEmpleado.getValue().toString()));
            nuevoContratos.setCodigocontratista(contratista);
            nuevoContratos.setCodigoempleado(empleados);
            nuevoContratos.setFechaingreso(fecha);
            
            
            contratoLogica.registrarContrato(nuevoContratos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se registró correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void seleccionarContrato(SelectEvent e){
        selectedContratos = (Contratos) e.getObject();
        txtCodContratista.setValue(selectedContratos.getCodigocontratista().getNitcontratista());
        txtCodEmpleado.setValue(selectedContratos.getCodigoempleado().getCedulaempleado());
    }
    
    public void modificarContratos() {
        try {
            Contratos nuevoContratos = selectedContratos;
            Contratista contratista = contratistaDAO.findxNit(Long.parseLong(txtCodContratista.getValue().toString()));
            Empleado empleados = empleadoDAO.findxCedula(Integer.parseInt(txtCodEmpleado.getValue().toString()));
            nuevoContratos.setCodigocontratista(contratista);
            nuevoContratos.setCodigoempleado(empleados);
            
            contratoLogica.modificarContrato(nuevoContratos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se modificó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarContratos(){
        try {
            contratoLogica.eliminarContrato(selectedContratos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se eliminó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
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
