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
    private static Part imagen;
    private String respuesta = "";

    //Obtener ruta fisica
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String realPath=(String) servletContext.getRealPath("/");
    
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

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String ingresarPartido() {
        return "insertarPartido";
    }

    public String insertarPartido(){
        partido.setImg("Hola");
        int resultado = partidoModel.insertarPartido(partido);
        if(resultado == 1){
            this.respuesta = "Partido insertado correctamente";
            partido = new PartidoEntity();
            return "listaPartidos";
        }
        FacesContext.getCurrentInstance().getMessageList().add(new FacesMessage("Nombre de partido ya existente"));
        return "insertarPartido";
    }
    
    public String deshabilitarPartido(int id){
        int resultado = partidoModel.eliminarPartido(id);
        if(resultado == 1){
            this.respuesta = "Partido deshabilitado correctamente";
        }
        FacesContext.getCurrentInstance().getMessageList().add(new FacesMessage("No se pudo deshabilitar el partido"));
        return "listaPartidos";
    }

}
