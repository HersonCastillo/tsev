
package sv.edu.udb.www.models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.EstadoCEEntity;

@Stateless
public class TipoEstadoEleccion {
    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public List<EstadoCEEntity> listaTipos(){
        Query query = em.createNamedQuery("EstadoCEEntity.findAll");
        return query.getResultList();
    }
}
