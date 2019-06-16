/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Ingreso;
import persistencia.IngresoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class IngresoLogica implements IngresoLogicaLocal {

    @EJB
    public IngresoFacadeLocal ingresoDAO;

    @Override
    public void registrarIngreso(Ingreso i) throws Exception {
        if (i == null) {
            throw new Exception("ERROR 345, el sistema se autodestruirá");
        }

        ingresoDAO.create(i);
    }

    @Override
    public List<Ingreso> consultarIngresos() {
        return ingresoDAO.findAll();
    }
}
