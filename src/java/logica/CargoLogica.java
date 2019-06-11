/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Cargo;
import persistencia.CargoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class CargoLogica implements CargoLogicaLocal {
    
    @EJB
    public CargoFacadeLocal cargoDAO;
    Cargo c = new Cargo();

    @Override
    public List<Cargo> consultarCargo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cargo consultarxCodigo(Integer codigo) throws Exception {
        c = cargoDAO.findxCodigo(codigo);
        return c;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
