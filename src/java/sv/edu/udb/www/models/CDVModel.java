/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.util.ArrayList;
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
            CDVEntity seleccione = em.find(CDVEntity.class, 1);
            Query query = em.createQuery("SELECT c FROM CDVEntity c WHERE c.idMunicipio.id = :municipio AND c.id != 1");
            query.setParameter("municipio", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de CDV (model) - " + ex.toString());
            return null;
        }
    }
    
    public List<CDVEntity> obtenerCDVPorMunicipioCombo(int id){
        try{
            CDVEntity seleccione = em.find(CDVEntity.class, 1);
            Query query = em.createQuery("SELECT c FROM CDVEntity c WHERE c.idMunicipio.id = :municipio AND c.id != 1");
            query.setParameter("municipio", id);
            List<CDVEntity> lista = new ArrayList();
            lista.add(seleccione);
            lista.addAll(query.getResultList());
            return lista;
        }catch(Exception ex){
            System.out.println("Error obteniendo la lista filtrada de CDV (model) - " + ex.toString());
            return null;
        }
    }
    
    public CDVEntity obtenerCDV(int id){
        return em.find(CDVEntity.class, id);
    }
}
