/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class UsuarioLogica implements UsuarioLogicaLocal {

    @EJB
    public UsuarioFacadeLocal usuarioDAO;

    @Override
    public Usuario ingresar(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (usuario.getNombreusuario().equals("")) {
            throw new Exception("Nombre de usuario obligatorio");
        }
        if (usuario.getClaveusuario().equals("")) {
            throw new Exception("Contraseña obligatoria");
        }

        Usuario objUsuario = usuarioDAO.findxNombre(usuario.getNombreusuario());

        if (objUsuario == null) {
            throw new Exception("Usuario no registrado");
        }
        if (!objUsuario.getClaveusuario().equals(usuario.getClaveusuario())) {
            throw new Exception("Clave incorrecta");
        }
        return objUsuario;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
