/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.models.MunicipioModel;

/**
 *
 * @author kevin
 */
@Named(value = "municipioBean")
@RequestScoped
public class MunicipioBean {

    @EJB
    private MunicipioModel municipioModel;
    
    private List<MunicipioEntity> listaMunicipios;

    public List<MunicipioEntity> getListaMunicipios() {
        return municipioModel.listaMunicipios();
    }

    public void setListaMunicipios(List<MunicipioEntity> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }
    
}
