/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Ingreso;

/**
 *
 * @author Kevin
 */
@Stateless
public class IngresoFacade extends AbstractFacade<Ingreso> implements IngresoFacadeLocal {

    @PersistenceContext(unitName = "DemoIngresoWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoFacade() {
        super(Ingreso.class);
    }

    @Override
    public List<Ingreso> findxEmpleado(int cedula) {
        String consulta = "select i from Ingreso i where i.empleadoingreso.cedulaempleado= "+ cedula;
        try {
            Query query = em.createQuery(consulta);
        return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
  
}
