/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.TipoEleccionEntity;
import sv.edu.udb.www.models.EleccionModel;
import sv.edu.udb.www.models.TipoEleccionModel;

/**
 *
 * @author kevin
 */
@Named(value = "eleccionBean")
@RequestScoped
public class eleccionBean {

    @EJB
    private TipoEleccionModel tipoEleccionModel;
    @EJB
    private EleccionModel eleccionModel;
    
    
    private EleccionEntity eleccion = new EleccionEntity();
    private boolean editando;
    private List<EleccionEntity> listaElecciones;
    private List<TipoEleccionEntity> listaTipos;
    private String respuesta = "";

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    
    public String ingresarInformacion(){
        return "ingresarEleccion";
    }

    public List<EleccionEntity> getListaElecciones() {
        return eleccionModel.obtenerElecciones();
    }

    public void setListaElecciones(List<EleccionEntity> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }

    public List<TipoEleccionEntity> getListaTipos() {
        return tipoEleccionModel.listaTipos();
    }

    public void setListaTipos(List<TipoEleccionEntity> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String insertarEleccion(){
        try{
            //Establecer dia actual como fecha de inicio
            eleccion.setFechIniRegistro(new Date());
            //Verificar que la fecha sea mayor a la actual
            if(eleccion.getFechFinRegistro().before(eleccion.getFechIniRegistro())){
                FacesContext.getCurrentInstance().addMessage("fechaFin", new FacesMessage("La fecha de finalizacion de registro debe ser mayor a la actual"));
            }
            //Verificar que la fecha de realizacio sea mayor a la de finalizacion de registro
            if(eleccion.getFechRealizacion().before(eleccion.getFechFinRegistro())){
                FacesContext.getCurrentInstance().addMessage("fechaRealizacion", new FacesMessage("La fecha de realizacion debe ser mayor a la de finalizacion de registro"));
            }
            //verificar que no se encuentren errores
            if(FacesContext.getCurrentInstance().getMessageList().isEmpty()){
                int resultado = eleccionModel.insertarEleccion(eleccion);
                if(resultado == 1){
                    this.respuesta = "Eleccion ingresada corrrectamente";
                    eleccion = new EleccionEntity();
                    return "listaElecciones";
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ya hay una eleccion de ese tipo pendiente"));
                return "ingresarEleccion";
            }
            return "ingresarEleccion";
        }catch(Exception ex){
            System.out.println("Error insertando eleccion (bean) - " + ex.toString());
            return "ingresarEleccion";
        }
    }
}
