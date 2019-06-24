/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Empleado;
import modelo.Ingreso;
import persistencia.EmpleadoFacadeLocal;
import persistencia.IngresoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class IngresoLogica implements IngresoLogicaLocal {

    @EJB
    public IngresoFacadeLocal ingresoDAO;
    @EJB
    public EmpleadoFacadeLocal empleadoDAO;

    @Override
    public void registrarIngreso(Ingreso i) throws Exception {
        if (i == null) {
            throw new Exception("Ingresar los campos");
        }
        if (i.getEmpleadoingreso() == null){
            throw new Exception("Ingresar el número de cédula del empleado");
        }
        Empleado objetoEmpleado= empleadoDAO.findxCedula(i.getEmpleadoingreso().getCedulaempleado());
        if(objetoEmpleado == null){
            throw new Exception ("El Empleado no existe");
        }
        ingresoDAO.create(i);
    }

    @Override
    public List<Ingreso> consultarIngresos() {
        return ingresoDAO.findAll();
    }
}
