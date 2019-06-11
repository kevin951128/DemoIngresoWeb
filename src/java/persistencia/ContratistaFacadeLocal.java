/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Local;
import modelo.Contratista;

/**
 *
 * @author Kevin
 */
@Local
public interface ContratistaFacadeLocal {

    void create(Contratista contratista);

    void edit(Contratista contratista);

    void remove(Contratista contratista);

    Contratista find(Object id);
    
    Contratista findxNit(Long nit);

    List<Contratista> findAll();

    List<Contratista> findRange(int[] range);

    int count();
    
}
