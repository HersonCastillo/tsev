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
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import sv.edu.udb.www.entities.CDVEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.models.CDVModel;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.MunicipioModel;

/**
 *
 * @author kevin
 */
@Named(value = "ciudadanoBean")
@RequestScoped
public class CiudadanoBean {

    @EJB
    private CDVModel cDVModel;
    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private DepartamentoModel departamentoModel;
    @EJB
    private CiudadanoModel ciudadanoModel;
    
    private static CiudadanoEntity ciudadano;
    private List<CiudadanoEntity> listaCiudadanos;
    private List<DepartamentoEntity> listaDepartamentos;
    private List<MunicipioEntity> listaMunicipiosPorDepartamento;
    private List<CDVEntity> listaCDVPorMunicipio;
    private static boolean editable;
    private static String respuesta = "";
    private Part imagen;
    
    //Obtener ruta fisica
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String realPath = (String) servletContext.getRealPath("/resources/img/ciudadanos");

    public CiudadanoEntity getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(CiudadanoEntity ciudadano) {
        this.ciudadano = ciudadano;
    }

    public List<CiudadanoEntity> getListaCiudadanos() {
        return ciudadanoModel.listaCiudadanos();
    }

    public void setListaCiudadanos(List<CiudadanoEntity> listaCiudadanos) {
        this.listaCiudadanos = listaCiudadanos;
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

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public List<DepartamentoEntity> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<MunicipioEntity> getListaMunicipiosPorDepartamento() {
        return listaMunicipiosPorDepartamento;
    }

    public void setListaMunicipiosPorDepartamento(List<MunicipioEntity> listaMunicipiosPorDepartamento) {
        this.listaMunicipiosPorDepartamento = listaMunicipiosPorDepartamento;
    }

    public List<CDVEntity> getListaCDVPorMunicipio() {
        return listaCDVPorMunicipio;
    }

    public void setListaCDVPorMunicipio(List<CDVEntity> listaCDVPorMunicipio) {
        this.listaCDVPorMunicipio = listaCDVPorMunicipio;
    }
    
    public void guardarImagen(String dui, int correlativo) {
        try {
            InputStream in = imagen.getInputStream();
            File f = new File(realPath + '/' + dui + correlativo + '_' + imagen.getSubmittedFileName());
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
                guardarImagen(ciudadano.getDui(), random);
            }else{
                File file = new File(realPath + '/' + nombreImagen);
                if (file.delete()) {
                    guardarImagen(ciudadano.getDui(), random);
                } else {
                    System.out.println("Error - No se borro la imagen");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error cambiando imagen (bean) - " + ex.toString());
        }
    }
    
    public String ingresarCiudadano(){
        this.editable = false;
        this.ciudadano = new CiudadanoEntity();
        this.respuesta = "";
        return "ingresoCiudadano";
    }
    
}
