/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import logica.ContratistaLogicaLocal;
import modelo.Contratista;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
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
    private InputText txtCedula;

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

    public InputText getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(InputText txtCedula) {
        this.txtCedula = txtCedula;
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

    public void seleccionarContratista(SelectEvent e) {
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

    public void eliminarContratista() {
        try {
            contratistaLogica.eliminarContratista(selectedContratista);
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

    public void handleFileUpload(FileUploadEvent event) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String rutaDestino = (String) servletContext.getRealPath("/excel"); // Sustituye "/" por el directorio ej: "/upload"

        System.out.println("Ruta Server: " + rutaDestino);
        try {
            copyFile(rutaDestino, event.getFile().getFileName(), event.getFile().getInputstream());
            String resultado = contratistaLogica.importarDatosContratista(rutaDestino + "\\" + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok: ", resultado));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", ex.getMessage()));
        }
    }

    public void copyFile(String rutaDestino, String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(rutaDestino + "\\" + fileName));
            System.out.println("Ruta Archivo: " + rutaDestino + "\\" + fileName);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            //System.out.println("New file created!");
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void generarReporte() {
        try {
            Connection conn = null;
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:app/jdbc/ingreso");
            conn = ds.getConnection();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/reporteContratistas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, conn);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=reporteContratistas.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarReporteParametros() throws Exception {
        try {
            Connection conn = null;
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:app/jdbc/ingreso");
            conn = ds.getConnection();
            Long documento = Long.parseLong(txtCedula.getValue().toString());
            Contratista objC = contratistaLogica.consultarxNit(documento);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigo", objC.getCodigocontratista());
            parametros.put("cedula", documento);
            parametros.put("nombre", objC.getNombrecontratista());
            parametros.put("telefono", objC.getEstadocontratista());
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/reporteIngresosContratista.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=reporteIngresoContratista" + documento + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
