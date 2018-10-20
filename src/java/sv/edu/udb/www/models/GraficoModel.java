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

/**
 *
 * @author kevin
 */
@Stateless
public class GraficoModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;
    
    public long nulosGlobales(int eleccion){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 3 AND DCE.idEleccion.id = :eleccion");
            query.setParameter("eleccion", eleccion);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos nulos a nivel global (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long sinGlobales(int eleccion){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 1 AND DCE.idEleccion.id = :eleccion");
            query.setParameter("eleccion", eleccion);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos sin realizar a nivel global (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long votosGlobales(int eleccion,int partido){
        try{
            Query query = em.createQuery("SELECT COUNT(V.id) FROM VotoEntity V WHERE V.idJRV.idEstado.id = 2 AND V.idJRV.idEleccion.id = :eleccion AND V.idCandidato.idPartido.id = :partido");
            query.setParameter("eleccion", eleccion);
            query.setParameter("partido", partido);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos globales (model) - " + ex.toString());
            return 0;
        }
    }

    public long nulosDepartamentales(int eleccion, int departamento){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 3 AND DCE.idEleccion.id = :eleccion AND DCE.idCiudadano.idCdv.idMunicipio.idDepartamento.id = :departamento");
            query.setParameter("eleccion", eleccion);
            query.setParameter("departamento", departamento);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos nulos a nivel departamental (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long sinDepartamentales(int eleccion, int departamento){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 1 AND DCE.idEleccion.id = :eleccion AND DCE.idCiudadano.idCdv.idMunicipio.idDepartamento.id = :departamento");
            query.setParameter("eleccion", eleccion);
            query.setParameter("departamento", departamento);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos nulos a nivel departamental (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long votosDepartamentales(int eleccion,int departamento,int partido){
        try{
            Query query = em.createQuery("SELECT COUNT(V.id) FROM VotoEntity V WHERE V.idJRV.idEstado.id = 2 AND V.idJRV.idEleccion.id = :eleccion AND V.idCandidato.idPartido.id = :partido AND V.idJRV.idCdv.idMunicipio.idDepartamento.id = :departamento");
            query.setParameter("eleccion", eleccion);
            query.setParameter("partido", partido);
            query.setParameter("departamento", departamento);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos departamental (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long nulosMunicipales(int eleccion, int municipio){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 3 AND DCE.idEleccion.id = :eleccion AND DCE.idCiudadano.idCdv.idMunicipio.id = :municipio");
            query.setParameter("eleccion", eleccion);
            query.setParameter("municipio", municipio);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos nulos a nivel municipal (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long sinMunicipales(int eleccion, int municipio){
        try{
            Query query = em.createQuery("SELECT COUNT(DCE) FROM DetalleCEEntity DCE WHERE DCE.idJrv.idEstado.id = 2 AND DCE.idEstado.id = 1 AND DCE.idEleccion.id = :eleccion AND DCE.idCiudadano.idCdv.idMunicipio.id = :municipio");
            query.setParameter("eleccion", eleccion);
            query.setParameter("municipio", municipio);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos nulos a nivel municipal (model) - " + ex.toString());
            return 0;
        }
    }
    
    public long votosMunicipales(int eleccion,int municipio,int partido){
        try{
            Query query = em.createQuery("SELECT COUNT(V.id) FROM VotoEntity V WHERE V.idJRV.idEstado.id = 2 AND V.idJRV.idEleccion.id = :eleccion AND V.idCandidato.idPartido.id = :partido AND V.idJRV.idCdv.idMunicipio.id = :municipio");
            query.setParameter("eleccion", eleccion);
            query.setParameter("partido", partido);
            query.setParameter("municipio", municipio);
            return (long) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo votos municipal (model) - " + ex.toString());
            return 0;
        }
    }
}
