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
import sv.edu.udb.www.entities.DetalleCEEntity;
import sv.edu.udb.www.entities.JRVEntity;
import sv.edu.udb.www.models.JRVModel;

/**
 *
 * @author kevin
 */
@Named(value = "jrvBean")
@RequestScoped
public class JRVBean {

    @EJB
    private JRVModel jrvModel;
    static int id;
    private List<JRVEntity> listaJrv;
    private List<DetalleCEEntity> listaCe;

    public List<JRVEntity> getListaJrv() {
        return jrvModel.obtenerJRVPorCDV(id);
    }

    public List<DetalleCEEntity> getListaCe() {
        return jrvModel.obtenerCiudPorJRV(id);
    }

    public void setListaCe(List<DetalleCEEntity> listaCe) {
        this.listaCe = listaCe;
    }
    
    public String obtenerCdvPormun(int id){
        this.id = id;
        return "listaJRV?faces-redirect=true";
    }
    public String obtenerCdvPormunC(int id){
        this.id = id;
        return "listaJRVCiud?faces-redirect=true";
    }
    public String obtenerCiudJrv(int id){
        this.id = id;
        return "listaCiudadanos?faces-redirect=true";
    }
    public void setListaJrv(List<JRVEntity> listaJrv) {
        this.listaJrv = listaJrv;
    }
    
}
