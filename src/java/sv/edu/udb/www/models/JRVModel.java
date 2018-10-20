/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.DetalleCEEntity;
import sv.edu.udb.www.entities.DetalleUJEntity;
import sv.edu.udb.www.entities.JRVEntity;
import sv.edu.udb.www.entities.RolEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class JRVModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public List<JRVEntity> obtenerJRVPorCDV(int id){
        try{
            Query query = em.createQuery("SELECT t FROM JRVEntity t WHERE t.idCdv.id = :cdv");
            query.setParameter("cdv", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de CDV (model) - " + ex.toString());
            return null;
        }
    }
    public DetalleUJEntity obtenerUsuario(int id){
        try{
            return em.find(DetalleUJEntity.class, id);
        }catch(Exception ex){
            System.out.println("Error obteniendo usuario (model) - " + ex.toString());
            return null;
        }
    }
    
    public JRVEntity obtenerJRV(int id){
        return em.find(JRVEntity.class, id);
    }
    
    public RolEntity obtenerRol(int id){
        return em.find(RolEntity.class, id);
    }
    
    public List<DetalleUJEntity> obtenerUsPorJRV(int id){
        try{
            Query query = em.createQuery("SELECT d FROM DetalleUJEntity d WHERE d.idJrv.id = :jrv");
            query.setParameter("jrv", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de JRV (model) - " + ex.toString());
            return null;
        }
    }
    
    public List<DetalleCEEntity> obtenerCiudPorJRV(int id){
        try{
            Query query = em.createQuery("SELECT d FROM DetalleCEEntity d WHERE d.idJrv.id = :jrv");
            query.setParameter("jrv", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de JRV (model) - " + ex.toString());
            return null;
        }
    }
    public List<JRVEntity> obetnerJRV(){
        try{
            Query q = em.createNamedQuery("JRVEntity.findAll");
            return q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public int insertarUsuarioDeJRV(DetalleUJEntity usuario){
        try{
            Query query = em.createQuery("SELECT d FROM DetalleUJEntity d WHERE d.idJrv = :jrv AND (d.idCiudadano = :ciud OR d.idRol = :rol) ");
            query.setParameter("ciud", usuario.getIdCiudadano());
            query.setParameter("jrv", usuario.getIdJrv());
            query.setParameter("rol", usuario.getIdRol());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                em.persist(usuario);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error insertando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public int actualizarUsuario(DetalleUJEntity usuario){
        try{
            Query query = em.createQuery("SELECT d FROM DetalleUJEntity d WHERE d.id != :id ");
            query.setParameter("id", usuario.getId());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                em.merge(usuario);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error actualizando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public int eliminarUsuario(int id){
        try{
            DetalleUJEntity usuario = em.find(DetalleUJEntity.class, id);
            em.remove(usuario);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error eliminando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public List<JRVEntity> obtenerJRVPorCDV(int cdv, int eleccion){
        try{
            Query query =em.createQuery("SELECT j FROM JRVEntity j WHERE j.idCdv.id = :cdv AND j.idEleccion.id = :eleccion");
            query.setParameter("cdv", cdv);
            query.setParameter("eleccion", eleccion);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo lista de jrv pot centro de votacion (model) - " + ex.toString());
            return null;
        }
    }
    public JRVEntity obtenerJRVById(int id){
        try{
            return em.find(JRVEntity.class, id);
        }catch(Exception ex){
            return null;
        }
    }
}
