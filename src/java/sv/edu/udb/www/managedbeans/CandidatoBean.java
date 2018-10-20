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
import sv.edu.udb.www.entities.CandidatoEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.PartidoEntity;
import sv.edu.udb.www.models.CandidatoModel;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.EleccionModel;
import sv.edu.udb.www.models.PartidoModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author kevin
 */
@Named(value = "candidatoBean")
@RequestScoped
public class CandidatoBean {

    @EJB
    private CiudadanoModel ciudadanoModel;
    @EJB
    private PartidoModel partidoModel;
    @EJB
    private EleccionModel eleccionModel;
    @EJB
    private CandidatoModel candidatoModel;

    private static CandidatoEntity candidato = new CandidatoEntity();
    private static String respuesta = "";
    private static boolean editable = false;
    private List<CandidatoEntity> listaCandidatos;
    private List<PartidoEntity> listaPartidos;
    private static EleccionEntity eleccion = new EleccionEntity();
    private static String dui = "";
    
    public CandidatoBean() {
    }

    public CandidatoEntity getCandidato() {
        return candidato;
    }

    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public List<CandidatoEntity> getListaCandidatos() {
        return candidatoModel.candidatosPorEleccion(eleccion.getId());
    }

    public void setListaCandidatos(List<CandidatoEntity> listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public List<PartidoEntity> getListaPartidos() {
        return partidoModel.listarPartidosHabilitados();
    }

    public void setListaPartidos(List<PartidoEntity> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }
    
    public String obtenerEleccion(int id){
        this.eleccion = eleccionModel.obtenerEleccion(id);
        return "listaCandidatosPresidenciales?faces-redirect=true";
    }
    
    public String obtenerEleccionMun(int id){
        this.eleccion = eleccionModel.obtenerEleccion(id);
        return "listaCandidatosMunicipales?faces-redirect=true";
    }
    
    public String ingresar(){
        this.candidato = new CandidatoEntity();
        this.respuesta = "";
        this.editable = false;
        this.dui ="";
        return "ingresarCandidato?faces-redirect=true";
    }
    
    public String ingresarM(){
        this.candidato = new CandidatoEntity();
        this.respuesta = "";
        this.editable = false;
        this.dui ="";
        return "ingresarCandidatoM?faces-redirect=true";
    }
    
    public String insertarCandidato(){
        try{
            CiudadanoEntity ciudadano = ciudadanoModel.obtenerCiudadanoPorDUI(this.dui);
            if(ciudadano == null){
                JsfUtils.addErrorMesages("dui", "No se encontro ningun ciudadano con el numero de DUI ingresado o la persona ya esta fallecida");
                return null;
            }
            candidato.setIdCiudano(ciudadano);
            candidato.setIdEleccion(this.eleccion);
            int resultado = candidatoModel.insertarCandidatoPresidencial(candidato);
            if(resultado == 1){
                this.respuesta = "Candidato ingresado";
                this.candidato = new CandidatoEntity();
                this.dui = "";
                return "listaCandidatosPresidenciales?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El ciudadano ya es candidato en esta eleccion u el partido ya tiene un candidato registrado");
            return null;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String insertarCandidatoM(){
        try{
            CiudadanoEntity ciudadano = ciudadanoModel.obtenerCiudadanoPorDUI(this.dui);
            if(ciudadano == null){
                JsfUtils.addErrorMesages("dui", "No se encontro ningun ciudadano con el numero de DUI ingresado");
                return null;
            }
            candidato.setIdCiudano(ciudadano);
            candidato.setIdEleccion(this.eleccion);
            int resultado = candidatoModel.insertarCandidatoMunicipal(candidato);
            if(resultado == 1){
                this.respuesta = "Candidato ingresado";
                this.candidato = new CandidatoEntity();
                this.dui = "";
                return "listaCandidatosMunicipales  ?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El ciudadano ya es candidato en esta eleccion u el partido ya tiene un candidato registrado");
            return null;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String cancelar(){
        this.respuesta = "";
        this.editable = false;
        this.candidato = new CandidatoEntity();
        this.dui = "";
        return "listaCandidatosPresidenciales?faces-redirect=true";
    }
    
    public String cancelarM(){
        this.respuesta = "";
        this.editable = false;
        this.candidato = new CandidatoEntity();
        this.dui = "";
        return "listaCandidatosMunicipales?faces-redirect=true";
    }
    
    public String eliminarCandidato(int id){
        try{
            int resultado = candidatoModel.eliminarCandidato(id);
            if(resultado == 1){
                this.respuesta = "Candidato eliminado";
            }
        }catch(Exception ex){
            System.out.println("Error eliminando candidato (bean) - " + ex.toString());
        }
        return null;
    }
    
    public String obtenerCandidato(int id){
        try{
            this.editable = true;
            this.candidato = candidatoModel.obtenerCandidato(id);
            this.dui = candidato.getIdCiudano().getDui();
            this.respuesta = "";
            return "ingresarCandidato?faces-redirect=true";
        }catch(Exception ex){
            System.out.println("Error obteniendo el candidato (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String actualizarCandidato(){
        try{
            CiudadanoEntity ciudadano = ciudadanoModel.obtenerCiudadanoPorDUI(this.dui);
            if(ciudadano == null){
                JsfUtils.addErrorMesages("dui", "No se encontro ningun ciudadano con el numero de DUI ingresado");
                return null;
            }
            candidato.setIdCiudano(ciudadano);
            candidato.setIdEleccion(this.eleccion);
            int resultado = candidatoModel.actualizarCandidatoPresidente(candidato);
            if(resultado == 1){
                this.respuesta = "Candidato actualizado";
                this.candidato = new CandidatoEntity();
                this.dui = "";
                return "listaCandidatosPresidenciales?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El ciudadano ya es candidato en esta eleccion u el partido ya tiene un candidato registrado");
            return null;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (bean) - " + ex.toString());
            return null;
        }
    }
}
