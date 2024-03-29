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
import persistencia.CargoFacadeLocal;
import persistencia.EmpleadoFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class EmpleadoLogica implements EmpleadoLogicaLocal {

    @EJB
    public EmpleadoFacadeLocal empleadoDAO;
    
    
    @Override
    public void RegistrarEmpleado(Empleado e) throws Exception {
         if (e == null) {
            throw new Exception("Error, los campos deben estar llenos");
        }
        if (e.getCedulaempleado()== 0) {
            throw new Exception ("Cedula obligatoria");
        }
        if (e.getNombreempleado().equals("")) {
            throw new Exception ("Nombre obligatorio");
        }
        if (e.getApellidoempleado().equals("")) {
            throw new Exception ("Apellido obligatorio");
        }
        if (e.getEstadoempleado().equals("")) {
            throw new Exception ("Estado del empleado obligatorio");
        }
        
        Empleado objetoContratista= empleadoDAO.findxCedula(e.getCedulaempleado());
        if(objetoContratista != null){
            throw new Exception ("El Empleado ya existe");
        }
        empleadoDAO.create(e);
    }

    @Override
    public List<Empleado> ConsultarEmpleados() {
        return empleadoDAO.findAll();
    }

    @Override
    public Empleado Consultarxcedula(Integer cedula) throws Exception {
        Empleado objEmpleado = empleadoDAO.findxCedula(cedula);
        return objEmpleado;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void modificarEmpleado(Empleado e) throws Exception {
        if (e == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (e.getCedulaempleado()== 0) {
            throw new Exception("Cédula obligatoria");
        }
        if (e.getNombreempleado().equals("")) {
            throw new Exception("Nombre obligatorio");
        }

        Empleado objetoEmpleado = empleadoDAO.findxCedula(e.getCedulaempleado());
        if (objetoEmpleado == null) {
            throw new Exception("El empleado no existe");
        }
        empleadoDAO.edit(e);
    }
    
    
    @Override
    public void eliminarEmpleado(Empleado e) throws Exception {
        if (e == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        
        Empleado objetoEmpleado = empleadoDAO.find(e.getCodigoempleado());
        if (objetoEmpleado == null) {
            throw new Exception("El empleado no existe");
        }
        
        empleadoDAO.remove(objetoEmpleado);
    }
}
