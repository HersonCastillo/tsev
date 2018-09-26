/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import java.util.Calendar;
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
    
    
    private static EleccionEntity eleccion = new EleccionEntity();
    private static boolean editando;
    private List<EleccionEntity> listaElecciones;
    private List<TipoEleccionEntity> listaTipos;
    private String respuesta = "";
    private String error = "";

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
        eleccion = new EleccionEntity();
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String insertarEleccion(){
        try{
            //Establecer dia actual como fecha de inicio
            eleccion.setFechIniRegistro(new Date());
            //Crear tipos de dato calendar
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(eleccion.getFechIniRegistro());
            Calendar finalizacion = Calendar.getInstance();
            finalizacion.setTime(eleccion.getFechFinRegistro());
            finalizacion.add(Calendar.DAY_OF_YEAR, 1);
            Calendar realizacion = Calendar.getInstance();
            realizacion.setTime(eleccion.getFechRealizacion());
            realizacion.add(Calendar.DAY_OF_YEAR, 1);
            //Verificar que la fecha sea mayor a la actual
            int fechaValidacion = finalizacion.compareTo(inicio);
            if(fechaValidacion < 0){
                FacesContext.getCurrentInstance().addMessage("fechaFin", new FacesMessage("La fecha de finalizacion de registro debe ser mayor a la actual"));
            }
            //Verificar que la fecha de realizacio sea mayor a la de finalizacion de registro
            fechaValidacion = realizacion.compareTo(finalizacion);
            if(fechaValidacion < 0){
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
    
    public String obtenerEleccion(int id){
        eleccion = eleccionModel.obtenerEleccion(id);
        this.editando = true;
        return "ingresarEleccion";
    }
    
    public String cancelar(){
        eleccion = new EleccionEntity();
        this.editando = false;
        return "listaElecciones";
    }
    
    public String actualizarEleccion(){
        try{
            //Crear tipos de dato calendar
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(new Date());
            Calendar finalizacion = Calendar.getInstance();
            finalizacion.setTime(eleccion.getFechFinRegistro());
            finalizacion.add(Calendar.DAY_OF_YEAR, 1);
            Calendar realizacion = Calendar.getInstance();
            realizacion.setTime(eleccion.getFechRealizacion());
            realizacion.add(Calendar.DAY_OF_YEAR, 1);
            //validar fecha de finalizacion de registro
            int fechaValidacion = finalizacion.compareTo(inicio);
            if(fechaValidacion < 0){
                FacesContext.getCurrentInstance().addMessage("fechaFin", new FacesMessage("La fecha de finalizacion de registro debe ser mayor igual a la actual"));
            }
            //validar fecha de realizacion
            fechaValidacion = realizacion.compareTo(finalizacion);
            if(fechaValidacion < 0){
                FacesContext.getCurrentInstance().addMessage("fechaRealizacion", new FacesMessage("La fecha de realizacion debe ser mayor a la de finalizacion de registro"));
            }
            //verificar que no se encuentren errores
            if(FacesContext.getCurrentInstance().getMessageList().isEmpty()){
                int resultado = eleccionModel.actualizarEleccion(eleccion);
                if(resultado == 1){
                    this.respuesta = "Eleccion actualizada corrrectamente";
                    eleccion = new EleccionEntity();
                    this.editando = false;
                    return "listaElecciones";
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ya hay una eleccion de ese tipo pendiente o esta eleccion ya tiene candidatos asignados"));
                return "ingresarEleccion";
            }
            return "ingresarEleccion";
        }catch(Exception ex){
            eleccion = eleccion;
            this.editando = true;
            System.out.println("Error actualizando eleccion (bean) - " + ex.toString());
            return "ingresarEleccion";
        }
    }

    public String DescartarEleccion(int id){
        try{
            int resultado = eleccionModel.descartarEleccion(id);
            if(resultado == 1){
                this.respuesta = "Eleccion descartada";
            }else{
                this.error = "No se pudo descartar";
            }
        }catch(Exception ex){
            System.out.println("Error descartando eleccion (bean) - " + ex.toString());
            error = "No se pudo descartar";
        }
        return "listaElecciones";
    }
}
