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
import sv.edu.udb.www.entities.MunicipioEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class MunicipioModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public List<MunicipioEntity> listaMunicipios(){
        Query query = em.createQuery("SELECT m FROM MunicipioEntity m WHERE m.id != 1");
        return query.getResultList();
    }
    
    public List<MunicipioEntity> listaMunicipiosPorDepartamento(int id){
        Query query = em.createQuery("SELECT m FROM MunicipioEntity m WHERE m.id != 1 AND m.idDepartamento.id = :departamento");
        query.setParameter("departamento", id);
        MunicipioEntity mun = em.find(MunicipioEntity.class, 1);
        List<MunicipioEntity> lista = new ArrayList();
        lista.add(mun);
        lista.addAll(query.getResultList());
        return lista;
    }
    
    public MunicipioEntity obtenerMunicipio(int id){
        return em.find(MunicipioEntity.class, id);
    }
}
