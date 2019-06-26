/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Contratos;

/**
 *
 * @author Kevin
 */
@Local
public interface ContratoLogicaLocal {
     public void registrarContrato(Contratos c) throws Exception;
    public List<Contratos> consultarContrato();
//    public Contratos consultarxCodigo(Integer codigo) throws Exception;
    public void modificarContrato(Contratos c) throws Exception;
    public void eliminarContrato (Contratos c) throws Exception;
    public List<Contratos> consultaxEmpleado(int cedula);
}
