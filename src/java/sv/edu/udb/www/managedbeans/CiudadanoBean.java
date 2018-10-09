/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.MunicipioModel;

/**
 *
 * @author kevin
 */
@Named(value = "ciudadanoBean")
@SessionScoped
public class CiudadanoBean implements Serializable {

    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private DepartamentoModel departamentoModel;
    @EJB
    private CiudadanoModel ciudadanoModel;
    
    private List<CiudadanoEntity> listaCiudadanos;
    private CiudadanoEntity ciudadano = new CiudadanoEntity();
    private Part imagen;
    private boolean editable;
    private String respuesta;
    //ComboBox anidados
    private List<DepartamentoEntity> listaDepartamentos;
    private static DepartamentoEntity departamento =  null;
    private List<MunicipioEntity> listaMunicipios;
    private MunicipioEntity municipio;
    

    public List<CiudadanoEntity> getListaCiudadanos() {
        return ciudadanoModel.listaCiudadanos();
    }

    public void setListaCiudadanos(List<CiudadanoEntity> listaCiudadanos) {
        this.listaCiudadanos = listaCiudadanos;
    }

    public CiudadanoEntity getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(CiudadanoEntity ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<DepartamentoEntity> getListaDepartamentos() {
        return departamentoModel.listaDepartamentosCombo();
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        if(this.departamento ==  null){
            return municipioModel.listaMunicipiosPorDepartamento(1);
        }else{
            return municipioModel.listaMunicipiosPorDepartamento(departamento.getId());
        }
    }

    public void setListaMunicipios(List<MunicipioEntity> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }
    
    public String ingresarCiudadano(){
        this.ciudadano = new CiudadanoEntity();
        this.respuesta = "";
        this.editable = false;
        return "ingresoCiudadano?faces-redirect=true";
    }
    
    public String cancelar(){
        return "listaCiudadanos?faces-redirect=true";
    }
}
