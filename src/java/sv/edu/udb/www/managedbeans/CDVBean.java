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
import sv.edu.udb.www.entities.CDVEntity;
import sv.edu.udb.www.models.CDVModel;

/**
 *
 * @author kevin
 */
@Named(value = "cdvBean")
@RequestScoped
public class CDVBean {

    @EJB
    private CDVModel cdvModel;
    static int id;
    private List<CDVEntity> listaCdv;

    public List<CDVEntity> getListaCdv() {
        return cdvModel.obtenerCDVPorMunicipio(id);
    }
    public String obtenerCdvPormun(int id){
        this.id = id;
        return "listaCDV?faces-redirect=true";
    }
    public String obtenerCdvPormunC(int id){
        this.id = id;
        return "listaCDVCiud?faces-redirect=true";
    }
    public void setListaMunicipios(List<CDVEntity> listaMunicipios) {
        this.listaCdv = listaCdv;
    }
    
}
