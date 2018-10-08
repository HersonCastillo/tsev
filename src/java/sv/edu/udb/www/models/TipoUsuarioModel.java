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
import sv.edu.udb.www.entities.TipoUsuarioEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class TipoUsuarioModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public List<TipoUsuarioEntity> listaTipos(){
        try{
            Query query = em.createQuery("SELECT t FROM TipoUsuarioEntity t WHERE t.id != 4");
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo lista de tipos de usuario (model) - " + ex.toString());
            return null;
        }
    }
}
