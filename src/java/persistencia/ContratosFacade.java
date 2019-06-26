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
import modelo.Contratos;

/**
 *
 * @author Kevin
 */
@Stateless
public class ContratosFacade extends AbstractFacade<Contratos> implements ContratosFacadeLocal {

    @PersistenceContext(unitName = "DemoIngresoWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratosFacade() {
        super(Contratos.class);
    }

    @Override
    public List<Contratos> findxEmpleado(int cedula) {
         String consulta = "select c from Contratos c where c.codigoempleado.cedulaempleado= "+ cedula;
        try {
            Query query = em.createQuery(consulta);
        return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
