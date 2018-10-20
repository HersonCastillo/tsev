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
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.DetalleUJEntity;
import sv.edu.udb.www.entities.JRVEntity;
import sv.edu.udb.www.entities.RolEntity;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.JRVModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author wecp123
 */
@Named(value = "rolesBean")
@RequestScoped
public class RolesBean {

    @EJB
    private JRVModel jrvModel;
    @EJB
    private CiudadanoModel ciudadanoModel;

    private static DetalleUJEntity us = new DetalleUJEntity();
    private static JRVEntity jrv = new JRVEntity();
    private static String respuesta = "";
    private static RolEntity rol;
    private static String dui = "";
    private static int id = 0;
    private static boolean editable = false;
    private static int rr = 0;
    private List<DetalleUJEntity> listaUs;

    public RolesBean() {

    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        RolesBean.editable = editable;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        RolesBean.dui = dui;
    }

    public static DetalleUJEntity getUs() {
        return us;
    }

    public static void setUs(DetalleUJEntity us) {
        RolesBean.us = us;
    }

    public static JRVEntity getJrv() {
        return jrv;
    }

    public static void setJrv(JRVEntity jrv) {
        RolesBean.jrv = jrv;
    }

    public List<DetalleUJEntity> getListaUs() {
        return jrvModel.obtenerUsPorJRV(id);
    }

    public String obtenerJRV(int id) {
        this.id = id;
        return "listaUsuariosJRV?faces-redirect=true";
    }

    public String obtenerRol(int id) {
        this.rol = jrvModel.obtenerRol(id);
        this.rr = id;
        this.editable = false;
        this.jrv = jrvModel.obtenerJRV(this.id);
        this.us = new DetalleUJEntity();
        this.respuesta = "";
        this.dui = "";
        return "ingresarUsuarioJrv?faces-redirect=true";
    }

    public String insertarCandidato() {
        try {
            CiudadanoEntity ciudadano = ciudadanoModel.obtenerCiudadanoPorDUI(this.dui);
            if (ciudadano == null) {
                JsfUtils.addErrorMesages("dui", "No se encontro ningun ciudadano con el numero de DUI ingresado o la persona ya esta fallecida");
                return null;
            }
            us.setIdCiudadano(ciudadano);
            us.setIdJrv(this.jrv);
            us.setIdRol(this.rol);
            int resultado = jrvModel.insertarUsuarioDeJRV(us);
            if (resultado == 1) {
                this.respuesta = "Usuario ingresado";
                this.us = new DetalleUJEntity();
                this.dui = "";
                this.editable = false;
                this.rr = 0;
                this.id = 0;
                return "listaUsuariosJRV?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El ciudadano ya es un usuario en esta jrv");
            return null;
        } catch (Exception ex) {
            System.out.println("Error insertando candidato (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String cancelar(){
        this.respuesta = "";
        this.us = new DetalleUJEntity();
        this.editable = false;
        this.dui = "";
        return "listaUsuariosJRV?faces-redirect=true";
    }
    
    public String eliminarUsuario(int id){
        try{
            int resultado = jrvModel.eliminarUsuario(id);
            if(resultado == 1){
                this.respuesta = "Candidato eliminado";
            }
        }catch(Exception ex){
            System.out.println("Error eliminando candidato (bean) - " + ex.toString());
        }
        return null;
    }
    
    public String obtenerUsuario(int id, int r){
        try{
            this.editable = true;
            this.us = jrvModel.obtenerUsuario(id);
            this.dui = us.getIdCiudadano().getDui();
            this.respuesta = "";
            this.rr = r;
            return "ingresarUsuarioJrv?faces-redirect=true";
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
            us.setIdCiudadano(ciudadano);
            us.setIdJrv(this.jrv);
            us.setIdRol(this.rol);
            int resultado = jrvModel.actualizarUsuario(us);
            if(resultado != 0){
                this.respuesta = "Usuario actualizado";
                this.us = new DetalleUJEntity();
                this.dui = "";
                this.editable = false;
                return "listaCandidatosPresidenciales?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El ciudadano ya es un usuario en esta jrv o ya no s eencuantra disponible este cargo");
            return null;
        }catch(Exception ex){
            System.out.println("Error insertando candidato (bean) - " + ex.toString());
            return null;
        }
    }
    
    public void setListaUs(List<DetalleUJEntity> listaUs) {
        this.listaUs = listaUs;
    }

    public int getRr() {
        return rr;
    }

    public void setRr(int rr) {
        RolesBean.rr = rr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        RolesBean.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        RolesBean.respuesta = respuesta;
    }

    public static RolEntity getRol() {
        return rol;
    }

    public static void setRol(RolEntity rol) {
        RolesBean.rol = rol;
    }
}
