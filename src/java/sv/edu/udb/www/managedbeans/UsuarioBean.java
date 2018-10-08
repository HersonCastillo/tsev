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
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.entities.TipoUsuarioEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.MunicipioModel;
import sv.edu.udb.www.models.TipoUsuarioModel;
import sv.edu.udb.www.models.UsuarioModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.JsfUtils;
import sv.edu.udb.www.utils.PasswordGenerator;
import sv.edu.udb.www.utils.SecurityUtils;

/**
 *
 * @author kevin
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    @EJB
    private DepartamentoModel departamentoModel;
    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private CiudadanoModel ciudadanoModel;
    @EJB
    private TipoUsuarioModel tipoUsuarioModel;
    @EJB
    private UsuarioModel usuarioModel;

    //usuario logueado
    private int userID = (int) JsfUtils.getRequest().getSession().getAttribute("id");
    private static UsuarioEntity usuario = new UsuarioEntity();
    private List<UsuarioEntity> listaAdministradores;
    private List<TipoUsuarioEntity> listaTipos;
    private List<DepartamentoEntity> listaDepartamentos;
    private List<MunicipioEntity> listaMunicipios;
    private static boolean editable;
    private static String respuesta = "";
    private static String error = "";
    private static String dui = "";
    private DepartamentoEntity departamento = new DepartamentoEntity();
    private MunicipioEntity municipio = new MunicipioEntity();
    private String clave = "";

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioEntity> getListaAdministradores() {
        return usuarioModel.listaAdministradores(userID);
    }

    public void setListaAdministradores(List<UsuarioEntity> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
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

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public List<TipoUsuarioEntity> getListaTipos() {
        return tipoUsuarioModel.listaTipos();
    }

    public void setListaTipos(List<TipoUsuarioEntity> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<DepartamentoEntity> getListaDepartamentos() {
        return departamentoModel.listaDepartamentosCombo();
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMuicipios(List<MunicipioEntity> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
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
            this.listaMunicipios = municipioModel.listaMunicipiosPorDepartamento(departamento.getId());
        } else {
            this.listaMunicipios = null;
        }
    }

    public String ingresarUsuario() {
        usuario = new UsuarioEntity();
        this.editable = false;
        this.respuesta = "";
        this.error = "";
        this.dui = "";
        this.departamento = null;
        this.municipio = null;
        return "ingresarUsuario?faces-redirect=true";
    }

    public String insertarUsuario() {
        try {
            CiudadanoEntity ciudadano = ciudadanoModel.obtenerCiudadanoPorDUI(dui);
            if (ciudadano == null) {
                JsfUtils.addErrorMesages("dui", "No se encontro ningun ciudadano con el DUI ingresado");
                return null;
            }
            usuario.setIdCiudadano(ciudadano);
            this.clave = PasswordGenerator.getPassword(PasswordGenerator.MINUSCULAS + PasswordGenerator.MAYUSCULAS + PasswordGenerator.ESPECIALES, 10);
            System.out.println(clave);
            usuario.setPassword(SecurityUtils.encriptarSHA(this.clave));
            usuario.setIdMunicipio(municipioModel.obtenerMunicipio(1));
            int resultado = usuarioModel.insertarUsuario(usuario);
            if (resultado == 1) {
                //Envio de correo
                String texto = "Bienvenido a la plataforma online del TSE!<br>";
                texto += "La clave que se le asigno para el ingreso es: " + this.clave;
                Correo correo = new Correo();
                correo.setAsunto("Registro de usaurio");
                correo.setMensaje(texto);
                correo.setDestinatario(usuario.getCorreo());
                correo.enviarCorreo();
                this.respuesta = "Usuario ingresado";
                this.usuario = new UsuarioEntity();
                this.error = "";
                this.dui = "";
                return "listaUsuarios?faces-redirect=true";
            }
            JsfUtils.addErrorMesages(null, "El correo ingresado ya se utilizo para registrar un usuario");
            return null;
        } catch (Exception ex) {
            System.out.println("Error insertando usuario (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String cancelar(){
        this.respuesta = "";
        this.editable = false;
        this.error = "";
        this.dui = "";
        this.usuario = new UsuarioEntity();
        return "listaUsuarios?faces-redirect=true";
    }

    public String eliminarUsuario(int id){
        try{
            int resultado = usuarioModel.eliminarUsuario(id);
            if(resultado == 1){
                this.respuesta = "Usuario eliminado";
            }else{
                this.error = "No se pudo eliminar";
            }
        }catch(Exception ex){
            this.error = "No se pudo eliminar";
            System.out.println("Error eliminando usuario (bean) - " + ex.toString());
        }
        return null;
    }
    
}
