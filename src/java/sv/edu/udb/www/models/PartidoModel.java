/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import sv.edu.udb.www.entities.EstadoPartidoEntity;
import sv.edu.udb.www.entities.PartidoEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class PartidoModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public int insertarPartido(PartidoEntity partido){
        try{
            Query query = em.createNamedQuery("PartidoEntity.findByNombre");
            query.setParameter("nombre", partido.getNombre());
            int count = query.getResultList().size();
            if(count == 0){
                partido.setIdEstado(em.find(EstadoPartidoEntity.class, 1));
                em.persist(partido);
                em.flush();
                return 1;
            }
            return 0;
        }catch(RuntimeException ex){
            System.out.println("Error insertando partido - " + ex.toString());
            return 0;
        }
    }
    
    public int actualizarPartido(PartidoEntity partido){
        try{
            Query query = em.createQuery("SELECT p FROM PartidoEntity p WHERE p.nombre = :nombre AND p.id != :id");
            query.setParameter("nombre", partido.getNombre());
            query.setParameter("id", partido.getId());
            int count = query.getResultList().size();
            if(count == 0){
                em.merge(partido);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error - actualizando partido: " + ex.toString());
            return 0;
        }
    }

    public int eliminarPartido(int id){
        try{
            PartidoEntity partido = em.find(PartidoEntity.class, id);
            partido.setIdEstado(em.find(EstadoPartidoEntity.class, 2));
            em.merge(partido);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error - deshabilitando partido: " + ex.toString());
            return 0;
        }
    }
    
    public List<PartidoEntity> listarPartidosHabilitados(){
        Query query = em.createQuery("SELECT p FROM PartidoEntity p WHERE p.idEstado.id = 1");
        return query.getResultList();
    }
    
    public List<PartidoEntity> listarPartidos(){
        Query query = em.createNamedQuery("PartidoEntity.findAll");
        return query.getResultList();
    }
    
    public PartidoEntity obtenerPartido(int id){
        return em.find(PartidoEntity.class, id);
    }
    
    public List<PartidoEntity> listaPartidosPorEleccion(int eleccion){
        try{
            Query query = em.createQuery("SELECT c.idPartido FROM CandidatoEntity c WHERE c.idEleccion.id = :eleccion");
            query.setParameter("eleccion", eleccion);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo los partidos por eleccion (model) - " + ex.toString());
            return null;
        }
    }
}
