/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
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

    public int insertarEleccion(EleccionEntity eleccion) {
        try {
            Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idTipo.id = :tipo AND (e.idEstado.id = 1 OR e.idEstado.id = 4 OR e.idEstado.id = 5)");
            query.setParameter("tipo", eleccion.getIdTipo().getId());
            int resultado = query.getResultList().size();
            if (resultado == 0) {
                StoredProcedureQuery store = em.createStoredProcedureQuery("crearEleccion");
                //Registrar parametros
                store.registerStoredProcedureParameter("inicio", Date.class, ParameterMode.IN);
                store.registerStoredProcedureParameter("fin", Date.class, ParameterMode.IN);
                store.registerStoredProcedureParameter("realizacion", Date.class, ParameterMode.IN);
                store.registerStoredProcedureParameter("tipo", Integer.class, ParameterMode.IN);
                //Setear parametros
                store.setParameter("inicio", eleccion.getFechIniRegistro());
                store.setParameter("fin", eleccion.getFechFinRegistro());
                store.setParameter("realizacion", eleccion.getFechRealizacion());
                store.setParameter("tipo", eleccion.getIdTipo().getId());
                //Respuesta
                store.execute();
                return 1;
                /*eleccion.setIdEstado(em.find(EstadoEleccionEntity.class, 1));
                em.persist(eleccion);
                em.flush();
                return 1;*/
            }
            return 0;
        } catch (Exception ex) {
            System.out.println("Error insertando eleccion - " + ex.toString());
            return 0;
        }
    }

    public int actualizarEleccion(EleccionEntity eleccion) {
        try {
            Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE (e.idEstado.id = 1 OR e.idEstado.id = 4 OR e.idEstado.id = 5) AND e.idTipo = :tipo AND e.id != :id");
            query.setParameter("tipo", eleccion.getIdTipo());
            query.setParameter("id", eleccion.getId());
            int resultado = query.getResultList().size();
            query = em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.idEleccion.id = :id AND c.idEleccion.idTipo != :tipo AND (c.idEleccion.idEstado.id = 1 OR c.idEleccion.idEstado.id = 4 OR c.idEleccion.idEstado.id = 5) ");
            query.setParameter("id", eleccion.getId());
            query.setParameter("tipo", eleccion.getIdTipo());
            int candidatos = query.getResultList().size();
            System.out.println(candidatos);
            if (resultado == 0 && candidatos == 0) {
                em.merge(eleccion);
                em.flush();
                return 1;
            }
            return 0;
        } catch (Exception ex) {
            System.out.println("Error actualizando eleccion - " + ex.toString());
            return 0;
        }
    }

    public List<EleccionEntity> obtenerElecciones() {
        Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idEstado.id != 3");
        return query.getResultList();
    }
    
    public List<EleccionEntity> obtenerEleccionesMun() {
        Query query = em.createNamedQuery("EleccionEntity.findByTipo");
        query.setParameter("id", 1);
        return query.getResultList();
    }

    public List<EleccionEntity> obtenerEleccionesRealizadas() {
        Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idEstado != 3");
        return query.getResultList();
    }

    public EleccionEntity obtenerEleccion(int id) {
        return em.find(EleccionEntity.class, id);
    }

    public int descartarEleccion(int id) {
        try {
            EleccionEntity eleccion = em.find(EleccionEntity.class, id);
            eleccion.setIdEstado(em.find(EstadoEleccionEntity.class, 3));
            em.merge(eleccion);
            em.flush();
            return 1;
        } catch (Exception ex) {
            System.out.println("Error descartando eleccion - " + ex.toString());
            return 0;
        }
    }
    
    public List<EleccionEntity> eleccionesPorTipo(int tipo){
        try{
            Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.idTipo.id = :tipo AND (e.idEstado.id = 5 OR e.idEstado.id = 2)");
            query.setParameter("tipo", tipo);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error listando las elecciones por tipo (model) - " + ex.toString());
            return null;
        }
    }
}
