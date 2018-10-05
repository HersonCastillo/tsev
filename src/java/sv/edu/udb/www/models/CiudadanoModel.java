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
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.EstadoCiudadanoEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class CiudadanoModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public int ingresarCiudadano(CiudadanoEntity ciudadano) {
        try {
            Query query = em.createQuery("SELECT c FROM CiudadanoEntity c WHERE c.dui = :dui");
            query.setParameter("dui", ciudadano.getDui());
            int resultado = query.getResultList().size();
            if (resultado == 0) {
                ciudadano.setIdEstado(em.find(EstadoCiudadanoEntity.class, 1));
                em.persist(ciudadano);
                em.flush();
                return 1;
            }
            return 0;
        } catch (Exception ex) {
            System.out.println("Error insertando ciudadano (model) - " + ex.toString());
            return -1;
        }
    }

    public int actualizarCiudadano(CiudadanoEntity ciudadano) {
        try {
            em.merge(ciudadano);
            em.flush();
            return 1;
        } catch (Exception ex) {
            System.out.println("Error modificando ciudadano (model) - " + ex.toString());
            return -1;
        }
    }

    public int eliminarCiudadano(int id) {
        try {
            CiudadanoEntity ciudadano = em.find(CiudadanoEntity.class, id);
            ciudadano.setIdEstado(em.find(EstadoCiudadanoEntity.class, 2));
            em.merge(ciudadano);
            em.flush();
            return 1;
        } catch (Exception ex) {
            System.out.println("Error degradando ciudadano (model) - " + ex.toString());
            return -1;
        }
    }

    public List<CiudadanoEntity> listaCiudadanos() {
        try {
            Query query = em.createQuery("SELECT c FROM CiudadanoEntity c WHERE c.id != 1");
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println("Error generando la lista de ciudadanos (model) - " + ex.toString());
            return null;
        }
    }

    public CiudadanoEntity obtenerCiudadano(int id) {
        try {
            return em.find(CiudadanoEntity.class, id);
        } catch (Exception ex) {
            System.out.println("Error obteniendo ciudadano (model) - " + ex.toString());
            return null;
        }
    }
    
    public CiudadanoEntity obtenerCiudadanoPorDUI(String dui){
        try{
            Query query = em.createQuery("SELECT c FROM CiudadanoEntity c WHERE c.dui = :dui");
            query.setParameter("dui", dui);
            return (CiudadanoEntity) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo ciudadano por DUI (model) - " + ex.toString());
            return null;
        }
    }
}
