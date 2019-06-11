/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Cargo;

/**
 *
 * @author Kevin
 */
@Local
public interface CargoLogicaLocal {
    public List<Cargo> consultarCargo();
    public Cargo consultarxCodigo(Integer codigo) throws Exception;
}
