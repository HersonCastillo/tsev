/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
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
    private Part imagen;
    private static String respuesta = "";
    private static boolean editando;

    //Obtener ruta fisica
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String realPath = (String) servletContext.getRealPath("/resources/img/banderas");
    
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

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String ingresarPartido() {
        partido = new PartidoEntity();
        this.editando = false;
        this.respuesta = "";
        return "insertarPartido?faces-redirect=true";
    }

    public String insertarPartido() {
        try {
            if(imagen.getSubmittedFileName().equals("")){
                FacesContext.getCurrentInstance().addMessage("imagen", new FacesMessage("La bandera del partido es obligatoria"));
                return "insertarPartido";
            }
            int random = (int) (Math.random() * 100000) + 1;
            partido.setImg(partido.getNombre() + random + "_" + imagen.getSubmittedFileName());
            int resultado = partidoModel.insertarPartido(partido);
            if (resultado == 1) {
                this.respuesta = "Partido insertado";
                guardarImagen(partido.getNombre(), random);
                partido = new PartidoEntity();
                return "listaPartidos?faces-redirect=true";
            }
            FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        } catch (Exception e) {
            System.out.println("Error insertando partido: " + e.toString());
            FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        }
    }

    public String deshabilitarPartido(int id) {
        try {
            int resultado = partidoModel.eliminarPartido(id);
            this.respuesta = "";
            if (resultado == 1) {
                this.respuesta = "Partido deshabilitado";
            }
        } catch (Exception ex) {
            System.out.println("Error deshabilitando partido - " + ex.toString());
        }
        return "listaPartidos?faces-redirect=true";
    }

    public String seleccionarPartido(int id) {
        partido = partidoModel.obtenerPartido(id);
        if (partido != null) {
            this.editando = true;
            this.respuesta = "";
            System.out.println(partido.getId());
            return "insertarPartido";
        }
        return "listarPartidos?faces-redirect=true";
    }

    public String actualizarPartido() {
        try {
            int random = 0;
            String imagenAntigua = partido.getImg();
            if(!imagen.getSubmittedFileName().equals("")){
                random = (int) (Math.random() * 100000) + 1;
                partido.setImg(partido.getNombre() + random + "_" + imagen.getSubmittedFileName());
            }
            int resultado = partidoModel.actualizarPartido(partido);
            if (resultado == 1) {
                this.respuesta = "Partido actualizado";
                if(!imagen.getSubmittedFileName().equals("")){
                    this.borrarImagen(imagenAntigua,random);
                }
                partido = new PartidoEntity();
                this.editando = false;
                return "listaPartidos?faces-redirect=true";
            }
            FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        } catch (Exception e) {
            System.out.println("Error actualizando: " + e.toString());
            FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage("Nombre de partido ya existente"));
            return "insertarPartido";
        }
    }

    public String cancelar() {
        this.partido = new PartidoEntity();
        this.editando = false;
        this.respuesta ="";
        return "listaPartidos?faces-redirect=true";
    }

    public void guardarImagen(String partido, int correlativo) {
        try {
            InputStream in = imagen.getInputStream();
            File f = new File(realPath + '/' + partido + correlativo + '_' + imagen.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void borrarImagen(String nombreImagen, int random) {
        try {
            boolean bandera = false;
            File dir = new File(realPath);
            String[] archivos = dir.list();
            for(String archivo : archivos ){
                if(archivo.equals(nombreImagen)){
                    bandera = true;
                    break;
                }
            }
            if(!bandera){
                //No se encontro ninguna imagen con ese nombre
                guardarImagen(partido.getNombre(), random);
            }else{
                File file = new File(realPath + '/' + nombreImagen);
                if (file.delete()) {
                    guardarImagen(partido.getNombre(), random);
                } else {
                    System.out.println("No se borro la imagen");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error cambiando imagen (bean) - " + ex.toString());
        }
    }
}
