
package sv.edu.udb.www.models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.DetalleUJEntity;

@Stateless
public class DetalleUJRV {
    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public DetalleUJEntity obtenerPresidente(String dui){
        try{
            List<DetalleUJEntity> detalle = this.obtenerDetalle();
            for (DetalleUJEntity d : detalle) {
                if(d.getIdRol().getId() == 1){
                    if(
                        d.getIdCiudadano().getDui().equals(dui) && 
                        d.getIdCiudadano().getIdEstado().getId() == 1 
                      ){
                        return d;
                    }
                }
            }
            return null;
        }catch(Exception ex){
            return null;
        }
    }
    public List<DetalleUJEntity> obtenerDetalle(){
        try{
            Query q = em.createNamedQuery("DetalleUJEntity.findAll");
            return q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
}
