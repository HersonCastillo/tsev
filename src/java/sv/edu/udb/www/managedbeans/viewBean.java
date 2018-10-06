/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.entities.CDVEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.models.CDVModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.MunicipioModel;

/**
 *
 * @author kevin
 */
@Named(value = "viewBean")
@ViewScoped
public class viewBean {

    @EJB
    private CDVModel cDVModel;
    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private DepartamentoModel departamentoModel;
    
    private List<DepartamentoEntity> listaDepartamentos;
    private static List<MunicipioEntity> listaMunicipiosPorDepartamento;
    private static List<CDVEntity> listaCDVPorMunicipio;
    private static DepartamentoEntity departamento;
    private static MunicipioEntity municipio;
    
    public viewBean() {
    }
    
     public List<DepartamentoEntity> getListaDepartamentos() {
        return departamentoModel.listaDepartamentosCombo();
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<MunicipioEntity> getListaMunicipiosPorDepartamento() {
        return listaMunicipiosPorDepartamento;
    }

    public void setListaMunicipiosPorDepartamento(List<MunicipioEntity> listaMunicipiosPorDepartamento) {
        this.listaMunicipiosPorDepartamento = listaMunicipiosPorDepartamento;
    }

    public List<CDVEntity> getListaCDVPorMunicipio() {
        return listaCDVPorMunicipio;
    }

    public void setListaCDVPorMunicipio(List<CDVEntity> listaCDVPorMunicipio) {
        this.listaCDVPorMunicipio = listaCDVPorMunicipio;
    }
    
    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }
    
    public void cambiarMunicipios() {
        if (this.departamento != null && this.departamento.getId() != 1) {
            this.listaMunicipiosPorDepartamento = municipioModel.listaMunicipiosPorDepartamento(departamento.getId());
            this.listaCDVPorMunicipio = null;
        } else {
            this.listaMunicipiosPorDepartamento = null;
            this.listaCDVPorMunicipio = null;

        }
    }

    public void cambiarCDV() {
        if (this.municipio != null && this.municipio.getId() != 1) {
            this.listaCDVPorMunicipio = cDVModel.obtenerCDVPorMunicipio(municipio.getId());
        } else {
            this.listaCDVPorMunicipio = null;
        }
    }
}
