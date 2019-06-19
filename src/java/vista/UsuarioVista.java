/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author Kevin
 */
@Named(value = "usuarioVista")
@RequestScoped
public class UsuarioVista {
    
    
    private InputText txtUsuario;
    private Password txtClave;
    private CommandButton btnIngresar;

    /**
     * Creates a new instance of UsuarioVista
     */
    public UsuarioVista() {
    }

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public Password getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(Password txtClave) {
        this.txtClave = txtClave;
    }

    public CommandButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(CommandButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }
    
    public void ingresar(){
        
    }
    
}
