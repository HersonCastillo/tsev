package sv.edu.udb.www.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.UsuarioEntity;

@Stateless
public class LoginModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

     public UsuarioEntity login(String email, String pass){
        try{
            UsuarioEntity user;
            String query = "SELECT u FROM UsuarioEntity u JOIN u.ciudadano c ON c.id = u.id_ciudadano WHERE c.correo = :correo AND u.password = :password";
            
            Query con = em.createQuery(query, UsuarioEntity.class);
            con.setParameter("correo", email);
            con.setParameter("password", pass);
            
            user = (UsuarioEntity) con.getSingleResult();
            return user;
        }catch(Exception e){
            return null;
        }
    }
    
}
