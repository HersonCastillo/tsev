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
    private String respuesta = "";
    private List<EleccionEntity> listaElecciones;
    private List<TipoEleccionEntity> listaTipos;

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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    public String ingresarInformacion(){
        this.eleccion = new EleccionEntity();
        this.editando = false;
        return "ingresarEleccion";
    }
    
    public void insertarEleccion(){
        try{
            System.out.println(eleccion.getIdTipo().getId());
        }catch(Exception ex){
            System.out.println("Error insertando eleccion (Bean) - " + ex.toString());
        }
    }
}
