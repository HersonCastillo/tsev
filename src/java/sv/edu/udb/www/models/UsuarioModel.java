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
import sv.edu.udb.www.entities.UsuarioEntity;

/**
 *
 * @author kevin
 */
@Stateless
public class UsuarioModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public int insertarUsuario(UsuarioEntity usuario){
        try{
            Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.correo = :correo");
            query.setParameter("correo", usuario.getCorreo());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                em.persist(usuario);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error insertando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public int actualizarUsuario(UsuarioEntity usuario){
        try{
            Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.correo = :correo AND u.id != :id");
            query.setParameter("correo", usuario.getCorreo());
            query.setParameter("id", usuario.getId());
            int resultado = query.getResultList().size();
            if(resultado == 0){
                em.merge(usuario);
                em.flush();
                return 1;
            }
            return 0;
        }catch(Exception ex){
            System.out.println("Error actualizando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public UsuarioEntity obtenerUsuario(int id){
        try{
            return em.find(UsuarioEntity.class, id);
        }catch(Exception ex){
            System.out.println("Error obteninedo usuario (model) - " + ex.toString());
            return null;
        }
    }
    
    public List<UsuarioEntity> listaAdministradores(int id){
        try{
            Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE (u.idTipo.id = 1 OR u.idTipo.id = 2 OR u.idTipo.id = 3) AND u.id != :id AND u.id != 1");
            query.setParameter("id", id);
            return query.getResultList();
        }catch(Exception ex){
            System.out.println("Error obteniendo lista de administradores (model) - " + ex.toString());
            return null;
        }
    }
    
    public int eliminarUsuario(int id){
        try{
            UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
            em.remove(usuario);
            em.flush();
            return 1;
        }catch(Exception ex){
            System.out.println("Error eliminando usuario (model) - " + ex.toString());
            return -1;
        }
    }
    
    public UsuarioEntity obtenerUsuarioPorClave(int id, String clave){
        try{
            Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.id = :id AND u.password = :clave");
            query.setParameter("id", id);
            query.setParameter("clave", clave);
            return (UsuarioEntity) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error obteniendo el usuario por id y clave (model) - " + ex.toString());
            return null;
        }
    }
}
