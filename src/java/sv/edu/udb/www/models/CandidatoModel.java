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
import sv.edu.udb.www.entities.CandidatoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class CandidatoModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public List<CandidatoEntity> listarCandidatos(){
        try{
            Query q = em.createNamedQuery("CandidatoEntity.findAll");
            return q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    public int insertarCandidatoPresidencial(CandidatoEntity candidato){
        try{
            Query query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion = :eleccion AND (c.idCiudano = :ciudadano OR c.idPartido = :partido) ");
            query.setParameter("eleccion", candidato.getIdEleccion());
            query.setParameter("ciudadano", candidato.getIdCiudano());
            query.setParameter("partido", candidato.getIdPartido());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                candidato.setIdMunicipio(em.find(MunicipioEntity.class, 1));
                em.persist(candidato);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (model) - " + ex.toString());
            return -1;
        }
    }
    public int insertarCandidatoMunicipal(CandidatoEntity candidato){
        try{
            Query query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion = :eleccion AND (c.idCiudano = :ciudadano OR c.idPartido = :partido) ");
            query.setParameter("eleccion", candidato.getIdEleccion());
            query.setParameter("ciudadano", candidato.getIdCiudano());
            query.setParameter("partido", candidato.getIdPartido());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                candidato.setIdMunicipio(em.find(MunicipioEntity.class, 1));
                em.persist(candidato);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (model) - " + ex.toString());
            return -1;
        }
    }

    public int actualizarCandidatoPresidente(CandidatoEntity candidato){
        try{
            Query query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion = :eleccion AND c.id != :id AND (c.idCiudano = :ciudadano OR c.idPartido = :partido) ");
            query.setParameter("eleccion", candidato.getIdEleccion());
            query.setParameter("id", candidato.getId());
            query.setParameter("ciudadano", candidato.getIdCiudano());
            query.setParameter("partido", candidato.getIdPartido());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                candidato.setIdMunicipio(em.find(MunicipioEntity.class, 1));
                em.merge(candidato);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error actualizando candidato (model) - " + ex.toString());
            return -1;
        }
    }
    
    public int actualizarCandidatoMunicipal(CandidatoEntity candidato){
        try{
            Query query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion = :eleccion AND c.id != :id AND (c.idCiudano = :ciudadano OR c.idPartido = :partido) ");
            query.setParameter("eleccion", candidato.getIdEleccion());
            query.setParameter("id", candidato.getId());
            query.setParameter("ciudadano", candidato.getIdCiudano());
            query.setParameter("partido", candidato.getIdPartido());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                candidato.setIdMunicipio(em.find(MunicipioEntity.class, 1));
                em.merge(candidato);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error actualizando candidato (model) - " + ex.toString());
            return -1;
        }
    }
    
    public CandidatoEntity obtenerCandidato(int id){
        try{
            return em.find(CandidatoEntity.class, id);
        }catch(Exception ex){
            System.out.println("Error obteniendo candidato (model) - " + ex.toString());
            return null;
        }
    }
    
    public List<CandidatoEntity> candidatosPorEleccion(int eleccion){
        try{
            Query query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion.id = :eleccion ");
            query.setParameter("eleccion", eleccion);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo lista de candidatos (model) - " + ex.toString());
            return null;
        }
    }
    
    public int eliminarCandidato(int id){
        try{
            CandidatoEntity candidato = em.find(CandidatoEntity.class, id);
            em.remove(candidato);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error eliminando candidato (model) - " + ex.toString());
            return -1;
        }
    }
}
