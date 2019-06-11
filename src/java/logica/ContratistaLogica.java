/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Contratista;
import persistencia.ContratistaFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class ContratistaLogica implements ContratistaLogicaLocal {

    //Entreprise java beans
    @EJB
    public ContratistaFacadeLocal contratistaDAO;
    
    @Override
    public void registrarContratista(Contratista c) throws Exception {
        if (c == null) {
            throw new Exception("ERROR 345, el sistema se autodestruirá");
        }
        if (c.getNitcontratista() == 0) {
            throw new Exception ("Nit obligatorio");
        }
        if (c.getNombrecontratista().equals("")) {
            throw new Exception ("Nombre obligatorio");
        }
        
        Contratista objetoContratista= contratistaDAO.findxNit(c.getNitcontratista());
        if(objetoContratista != null){
            throw new Exception ("El contratista ya existee!!");
        }
        contratistaDAO.create(c);
        
    }
    
    @Override
    public List<Contratista> consultarContratistas() {
        return contratistaDAO.findAll();
    }
    
    @Override
    public Contratista consultarxCodigo(Integer codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
