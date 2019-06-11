/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Empleado;

/**
 *
 * @author Kevin
 */
@Local
public interface EmpleadoLogicaLocal {
    public void RegistrarEmpleado(Empleado e) throws Exception;
    public List<Empleado> ConsultarEmpleados();
    public Empleado Consultarxcedula(Integer cedula) throws Exception;
}
