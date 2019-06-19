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
import logica.ContratistaLogicaLocal;
import modelo.Contratista;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Kevin
 */
@Named(value = "contratistaVista")
@RequestScoped
public class ContratistaVista {

    @EJB
    private ContratistaLogicaLocal contratistaLogica;

    private List<Contratista> listaContratistas;
    private InputText txtNitContratista;
    private InputText txtNombreContratista;
    private SelectOneMenu cmbEstadoContratista;
    private CommandButton bntRegistrar;
    private CommandButton btnModificar;
    private CommandButton bntLimpiar;
    private CommandButton bntEliminar;
    private Contratista selectedContratista;
    

    /**
     * Creates a new instance of ContratistaVista
     */
    public ContratistaVista() {
    }

    public InputText getTxtNitContratista() {
        return txtNitContratista;
    }

    public void setTxtNitContratista(InputText txtNitContratista) {
        this.txtNitContratista = txtNitContratista;
    }

    public InputText getTxtNombreContratista() {
        return txtNombreContratista;
    }

    public void setTxtNombreContratista(InputText txtNombreContratista) {
        this.txtNombreContratista = txtNombreContratista;
    }

    public SelectOneMenu getCmbEstadoContratista() {
        return cmbEstadoContratista;
    }

    public void setCmbEstadoContratista(SelectOneMenu cmbEstadoContratista) {
        this.cmbEstadoContratista = cmbEstadoContratista;
    }

    public CommandButton getBntRegistrar() {
        return bntRegistrar;
    }

    public void setBntRegistrar(CommandButton bntRegistrar) {
        this.bntRegistrar = bntRegistrar;
    }

    public List<Contratista> getListaContratistas() {
        listaContratistas = contratistaLogica.consultarContratistas();
        return listaContratistas;
    }

    public void setListaContratistas(List<Contratista> listaContratistas) {
        this.listaContratistas = listaContratistas;
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

    public Contratista getSelectedContratista() {
        return selectedContratista;
    }

    public void setSelectedContratista(Contratista selectedContratista) {
        this.selectedContratista = selectedContratista;
    }

    public void registrarContratista() {
        try {
            Contratista nuevoContratista = new Contratista();
            nuevoContratista.setNitcontratista(Long.parseLong(txtNitContratista.getValue().toString()));
            nuevoContratista.setNombrecontratista(txtNombreContratista.getValue().toString());
            nuevoContratista.setEstadocontratista(cmbEstadoContratista.getValue().toString());
            
            contratistaLogica.registrarContratista(nuevoContratista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se registró correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void seleccionarContratista(SelectEvent e){
        selectedContratista = (Contratista) e.getObject();
        txtNitContratista.setValue(selectedContratista.getNitcontratista());
        txtNombreContratista.setValue(selectedContratista.getNombrecontratista());
        cmbEstadoContratista.setValue(selectedContratista.getEstadocontratista());
    }

     public void modificarContratista() {
        try {
            Contratista nuevoContratista = selectedContratista;
            nuevoContratista.setNitcontratista(Long.parseLong(txtNitContratista.getValue().toString()));
            nuevoContratista.setNombrecontratista(txtNombreContratista.getValue().toString());
            nuevoContratista.setEstadocontratista(cmbEstadoContratista.getValue().toString());
            
            contratistaLogica.modificarContratista(nuevoContratista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Muy bien:", "Se modificó correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void eliminarContratista(){
        try {
            contratistaLogica.eliminarContratista(selectedContratista);
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
