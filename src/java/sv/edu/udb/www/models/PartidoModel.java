/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
            em.persist(partido);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error - insertando partido: " + ex.toString());
            return 0;
        }
    }
    
    public int actualizarPartido(PartidoEntity partido){
        try{
            em.merge(partido);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error - actualizando partido: " + ex.toString());
            return 0;
        }
    }

}
