/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.converters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import sv.edu.udb.www.entities.TipoEleccionEntity;

/**
 *
 * @author kevin
 */
@FacesConverter(value = "sv.edu.udb.www.converters.TipoEleccionConverter")
public class TipoEleccionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TSEVPU");
            EntityManager em = emf.createEntityManager();
            TipoEleccionEntity tipo = em.find(TipoEleccionEntity.class, id);
            return tipo;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((TipoEleccionEntity) value).getId().toString();
    }

}
