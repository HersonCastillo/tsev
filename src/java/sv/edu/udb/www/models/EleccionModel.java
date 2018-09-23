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
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.EstadoEleccionEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class EleccionModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public int insertarEleccion(EleccionEntity eleccion){
        try{
            Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idEstado = 1 AND e.idTipo = :tipo");
            query.setParameter("tipo", eleccion.getIdTipo().getId());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                em.persist(eleccion);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error insertando eleccion - " + ex.toString());
            return 0;
        }
    }

    public int actualizarEleccion(EleccionEntity eleccion){
        try{
            em.merge(eleccion);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error actualizando eleccion - " + ex.toString());
            return 0;
        }
    }
    
    public List<EleccionEntity> obtenerElecciones(){
        Query query = em.createNamedQuery("EleccionEntity.findAll");
        return query.getResultList();
    }
    
    public List<EleccionEntity> obtenerEleccionesRealizadas(){
        Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idEstado != 3");
        return query.getResultList();
    }
    
    public int descartarEleccion(int id){
        try{
            EleccionEntity eleccion = em.find(EleccionEntity.class, id);
            eleccion.setIdEstado(em.find(EstadoEleccionEntity.class, 3));
            em.merge(eleccion);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error descartando eleccion - " + ex.toString());
            return 0;
        }
    }
}
