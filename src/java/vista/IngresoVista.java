/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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

    /**
     * Creates a new instance of IngresoVista
     */
    public IngresoVista() {
        
        
        
                
        
    }
    
}
