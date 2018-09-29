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
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.models.DepartamentoModel;

/**
 *
 * @author kevin
 */
@Named(value = "departamentoBean")
@RequestScoped
public class DepartamentoBean {

    @EJB
    private DepartamentoModel departamentoModel;

    private List<DepartamentoEntity> listaDepartamentos;
    private List<DepartamentoEntity> listaFiltrada;

    public List<DepartamentoEntity> getListaDepartamentos() {
        return departamentoModel.listaDepartamentos();
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<DepartamentoEntity> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<DepartamentoEntity> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    
}
