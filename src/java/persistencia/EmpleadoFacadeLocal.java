/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Local;
import modelo.Empleado;

/**
 *
 * @author Kevin
 */
@Local
public interface EmpleadoFacadeLocal {

    void create(Empleado empleado);

    void edit(Empleado empleado);

    void remove(Empleado empleado);

    Empleado find(Object id);
    
    Empleado findxCedula(long cedula);

    List<Empleado> findAll();

    List<Empleado> findRange(int[] range);

    int count();
    
}
