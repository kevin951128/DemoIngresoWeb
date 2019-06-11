/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Cargo;

/**
 *
 * @author Kevin
 */
@Stateless
public class CargoFacade extends AbstractFacade<Cargo> implements CargoFacadeLocal {

    @PersistenceContext(unitName = "DemoIngresoWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }

    @Override
    public Cargo findxCodigo(Integer codigo) {
               String consulta = "select c from Contratista c where c.nitcontratista= "+ codigo;
        try {
            Query query = em.createQuery(consulta);
        return (Cargo) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
