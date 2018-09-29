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
import sv.edu.udb.www.entities.CDVEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class CDVModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public List<CDVEntity> obtenerCDVPorMunicipio(int id){
        try{
            Query query = em.createQuery("SELECT c FROM CDVEntity c WHERE c.idMunicipio.id = :municipio");
            query.setParameter("municipio", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de CDV (model) - " + ex.toString());
            return null;
        }
    }
}
