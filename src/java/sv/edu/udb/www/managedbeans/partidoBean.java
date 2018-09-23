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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;
import sv.edu.udb.www.entities.PartidoEntity;
import sv.edu.udb.www.models.PartidoModel;

/**
 *
 * @author kevin
 */
@Named(value = "partidoBean")
@RequestScoped
public class partidoBean {

    @EJB
    private PartidoModel partidoModel;
    private static PartidoEntity partido = new PartidoEntity();
    private List<PartidoEntity> listaPartidos;
    private static UploadedFile imagen;
    private String respuesta = "";
    private static boolean editando;

    //Obtener ruta fisica
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String realPath = (String) servletContext.getRealPath("/");

    public partidoBean() {
    }

    public PartidoEntity getPartido() {
        return partido;
    }

    public void setPartido(PartidoEntity partido) {
        this.partido = partido;
    }

    public List<PartidoEntity> getListaPartidos() {
        return partidoModel.listarPartidos();
    }

    public void setListaPartidos(List<PartidoEntity> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String ingresarPartido() {
        partido = new PartidoEntity();
        this.editando = false;
        return "insertarPartido";
    }

    public String insertarPartido() {
        try {
            partido.setImg("Hola");
            int resultado = partidoModel.insertarPartido(partido);
            if (resultado == 1) {
                this.respuesta = "Partido insertado correctamente";
                partido = new PartidoEntity();
                return "listaPartidos";
            }
            FacesContext.getCurrentInstance().addMessage("nombre",new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        } catch (Exception e) {
            System.out.println("Error insertando partido: " + e.toString());
            FacesContext.getCurrentInstance().addMessage("nombre",new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        }
    }

    public String deshabilitarPartido(int id) {
        try {
            int resultado = partidoModel.eliminarPartido(id);
            if (resultado == 1) {
                this.respuesta = "Partido deshabilitado correctamente";
            }
        } catch (Exception ex) {
            System.out.println("Error deshabilitando partido - " + ex.toString());
        }
        return "listaPartidos";
    }

    public String seleccionarPartido(int id) {
        partido = partidoModel.obtenerPartido(id);
        if (partido != null) {
            this.editando = true;
            System.out.println(partido.getId());
            return "insertarPartido";
        }
        return "listarPartidos";
    }

    public String actualizarPartido() {
        try {
            int resultado = partidoModel.actualizarPartido(partido);
            if (resultado == 1) {
                this.respuesta = "Partido actualizado correctamente";
                partido = new PartidoEntity();
                this.editando = false;
                return "listaPartidos";
            }
            FacesContext.getCurrentInstance().addMessage("nombre",new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        } catch (Exception e) {
            System.out.println("Error actualizando: " + e.toString());
            FacesContext.getCurrentInstance().addMessage("nombre",new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        }
    }
    
    public String cancelar(){
        this.partido = new PartidoEntity();
        this.editando = false;
        return "listaPartidos";
    }
    
}
