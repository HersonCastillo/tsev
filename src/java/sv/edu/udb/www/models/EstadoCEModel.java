
package sv.edu.udb.www.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.www.entities.EstadoCEEntity;
@Stateless
public class EstadoCEModel {
    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public EstadoCEEntity obtenerEstadoCEById(int id){
        try{
            return em.find(EstadoCEEntity.class, id);
        }catch(Exception ex){
            return null;
        }
    }
}
