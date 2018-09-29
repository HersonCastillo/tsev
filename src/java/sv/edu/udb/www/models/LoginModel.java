package sv.edu.udb.www.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.utils.SecurityUtils;

@Stateless
public class LoginModel {

    @PersistenceContext(unitName = "TSEVPU")
    private EntityManager em;

    public UsuarioEntity login(String email, String pass) {
        try {
            Query query = em.createNamedQuery("UsuarioEntity.checkLogin");
            query.setParameter("correo", email);
            query.setParameter("password", SecurityUtils.encriptarSHA(pass));
            System.out.println(query.getSingleResult());
            return (UsuarioEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
