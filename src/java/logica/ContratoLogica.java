/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Contratos;
import persistencia.ContratosFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class ContratoLogica implements ContratoLogicaLocal {

    @EJB
    public ContratosFacadeLocal contratosDAO;
    @Override
    public void registrarContrato(Contratos c) throws Exception {
       if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (c.getCodigocontratista() == null) {
            throw new Exception("Nit obligatorio");
        }
        if (c.getCodigoempleado()== null) {
            throw new Exception("Nit obligatorio");
        }

        contratosDAO.create(c);
    }
    @Override
    public List<Contratos> consultarContrato() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarContrato(Contratos c) throws Exception {
       if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (c.getCodigocontratista()== null) {
            throw new Exception("Nit obligatorio");
        }
        if (c.getCodigoempleado()== null) {
            throw new Exception("Nombre obligatorio");
        }

       
        contratosDAO.edit(c);
    }

    @Override
    public void eliminarContrato(Contratos c) throws Exception {
       if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        Contratos objBorrar = contratosDAO.find(c.getNumerocontrato());
        if(objBorrar == null) {
            throw new Exception("Error, contratista no existe");
        }
        //Validación que el contratista no tenga ingresos ni contratos 
        
        contratosDAO.remove(objBorrar);
    }

    @Override
    public List<Contratos> consultaxEmpleado(int cedula) {
        return contratosDAO.findxEmpleado(cedula);
    }

   

    
}
