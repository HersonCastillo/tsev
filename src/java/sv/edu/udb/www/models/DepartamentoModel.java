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
import sv.edu.udb.www.entities.DepartamentoEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class DepartamentoModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public List<DepartamentoEntity> listaDepartamentos(){
        try{
            Query query = em.createQuery("SELECT d FROM DepartamentoEntity d WHERE d.id != 1");
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error listando departamentos (model) - " + ex.toString());
            return null;
        }
    }
}