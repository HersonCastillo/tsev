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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sv.edu.udb.www.entities.DepartamentoEntity;

/**
 *
 * @author kevin
 */
@FacesConverter(value = "sv.edu.udb.www.converters.DepartamentoConverter")
public class DepartamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TSEVPU");
            EntityManager em = emf.createEntityManager();
            DepartamentoEntity departamento = em.find(DepartamentoEntity.class, id);
            return departamento;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((DepartamentoEntity)value).getId().toString();
    }
    
}
