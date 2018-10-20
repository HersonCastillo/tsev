
package sv.edu.udb.www.models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.VotoEntity;
import sv.edu.udb.www.entities.DetalleCEEntity;

@Stateless
public class VotoModel {
    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public boolean crearVoto(VotoEntity voto){
        try{
            em.persist(voto);
            em.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public boolean agregarDetalle(DetalleCEEntity dce){
        try{
            em.persist(dce);
            em.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public boolean eliminarDetalle(int id){
        try{
            DetalleCEEntity dce = em.find(DetalleCEEntity.class, id);
            if(dce != null){
                em.remove(dce);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    public List<DetalleCEEntity> listarDetalle(){
        try{
            Query q = em.createNamedQuery("DetalleCEEntity.findAll");
            return q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    public DetalleCEEntity obtenerDetalle(int id){
        return em.find(DetalleCEEntity.class, id);
    }
    public List<VotoEntity> obtenerVotos(){
        try{
            Query q = em.createNamedQuery("VotoEntity.findAll");
            return q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    public boolean modificarDetalle(DetalleCEEntity d){
        try{
            em.merge(d);
            em.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public boolean eliminarVoto(int id){
        try{
            VotoEntity voto = em.find(VotoEntity.class, id);
            if(voto != null){
                em.remove(voto);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception ex){
            return false;
        }
    }
}
