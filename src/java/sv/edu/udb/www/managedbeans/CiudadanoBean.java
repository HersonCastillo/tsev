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
import java.util.Calendar;
import java.util.Date;
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
import sv.edu.udb.www.utils.JsfUtils;

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

    private static CiudadanoEntity ciudadano = new CiudadanoEntity();
    private List<CiudadanoEntity> listaCiudadanos;
    private List<DepartamentoEntity> listaDepartamentos;
    private static List<MunicipioEntity> listaMunicipiosPorDepartamento;
    private static List<CDVEntity> listaCDVPorMunicipio;
    private static boolean editable;
    private static String respuesta = "";
    private Part imagen;
    private static DepartamentoEntity departamento;
    private static MunicipioEntity municipio;
    private String direccion;

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
        return departamentoModel.listaDepartamentosCombo();
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void guardarImagen(String dui) {
        try {
            InputStream in = imagen.getInputStream();
            File f = new File(realPath + '/' + dui + '_' + imagen.getSubmittedFileName());
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

    public void borrarImagen(String nombreImagen) {
        try {
            boolean bandera = false;
            File dir = new File(realPath);
            String[] archivos = dir.list();
            for (String archivo : archivos) {
                if (archivo.equals(nombreImagen)) {
                    bandera = true;
                    break;
                }
            }
            if (!bandera) {
                //No se encontro ninguna imagen con ese nombre
                guardarImagen(ciudadano.getDui());
            } else {
                File file = new File(realPath + '/' + nombreImagen);
                if (file.delete()) {
                    guardarImagen(ciudadano.getDui());
                } else {
                    System.out.println("Error - No se borro la imagen");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error cambiando imagen (bean) - " + ex.toString());
        }
    }

    public String ingresarCiudadano() {
        this.departamento = null;
        this.municipio = null;
        this.editable = false;
        this.ciudadano = new CiudadanoEntity();
        this.respuesta = "";
        return "ingresoCiudadano";
    }

    public void cambiarMunicipios() {
        if (this.departamento != null && this.departamento.getId() != 1) {
            this.listaMunicipiosPorDepartamento = municipioModel.listaMunicipiosPorDepartamento(departamento.getId());
            this.listaCDVPorMunicipio = null;
        } else {
            this.listaMunicipiosPorDepartamento = null;
            this.listaCDVPorMunicipio = null;

        }
    }

    public void cambiarCDV() {
        if (this.municipio != null && this.municipio.getId() != 1) {
            this.listaCDVPorMunicipio = cDVModel.obtenerCDVPorMunicipio(municipio.getId());
        } else {
            this.listaCDVPorMunicipio = null;
        }
    }

    public String insertar() {
        try {
            //validar imagen
            if (imagen.getSubmittedFileName().equals("")) {
                JsfUtils.addErrorMesages("imagen", "La foto es necesaria");
            }
            //validar fecha
            Date now = new Date();
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(now);
            Calendar finalizacion = Calendar.getInstance();
            finalizacion.setTime(ciudadano.getFechNac());
            long mili = inicio.getTimeInMillis() - finalizacion.getTimeInMillis();
            long y = mili / 365 / 24 / 60 / 60 / 1000;
            if (y < 18) {
                JsfUtils.addErrorMesages("fechaNac", "El ciudadano debe ser mayor de 18");
            }
            //validar sexo
            if(ciudadano.getGenero() == 0){
                JsfUtils.addErrorMesages("genero", "Debe seleccionar el sexo");
            }
            //validar direccion
            if (this.direccion.trim().equals("") || this.direccion.trim().length() == 0) {
                JsfUtils.addErrorMesages("direccion", "La direccion es necesaria");
            }
            //validar cdv
            if (ciudadano.getIdCdv() == null) {
                JsfUtils.addErrorMesages("cdv", "Debe seleccionar un centro de votacion");
            }
            if (FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
                ciudadano.setDireccion(direccion);
                ciudadano.setImg(ciudadano.getDui() + '_' + imagen.getSubmittedFileName());
                int resultado = ciudadanoModel.ingresarCiudadano(ciudadano);
                if (resultado == 1) {
                    guardarImagen(ciudadano.getDui());
                    this.respuesta = "Ciudadano ingresado";
                    this.departamento = null;
                    this.municipio = null;
                    ciudadano = new CiudadanoEntity();
                    return "listaCiudadanos?faces-redirect=true";
                }
                JsfUtils.addErrorMesages(null, "Numero de DUI repetido");
            } else {
                return null;
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Problemas insertando ciudadano (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String cancelar(){
        ciudadano = new CiudadanoEntity();
        this.municipio = null;
        this.editable = false;
        this.ciudadano = new CiudadanoEntity();
        this.respuesta = "";
        this.direccion = "";
        return "listaCiudadanos?faces-redirect=true";
    }
    
    public String obtenerCiudadano(int id){
        ciudadano = ciudadanoModel.obtenerCiudadano(id);
        editable = true;
        departamento = ciudadano.getIdCdv().getIdMunicipio().getIdDepartamento();
        listaMunicipiosPorDepartamento = municipioModel.listaMunicipiosPorDepartamento(departamento.getId());
        municipio = ciudadano.getIdCdv().getIdMunicipio();
        listaCDVPorMunicipio = cDVModel.obtenerCDVPorMunicipio(municipio.getId());
        direccion = ciudadano.getDireccion();
        return "ingresoCiudadano";
    }
    
    public String actualizar(){
        try {
            //validar fecha
            Date now = new Date();
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(now);
            Calendar finalizacion = Calendar.getInstance();
            finalizacion.setTime(ciudadano.getFechNac());
            long mili = inicio.getTimeInMillis() - finalizacion.getTimeInMillis();
            long y = mili / 365 / 24 / 60 / 60 / 1000;
            if (y < 18) {
                JsfUtils.addErrorMesages("fechaNac", "El ciudadano debe ser mayor de 18");
            }
            //validar sexo
            if(ciudadano.getGenero() == 0){
                JsfUtils.addErrorMesages("genero", "Debe seleccionar el sexo");
            }
            //validar direccion
            if (this.direccion.trim().equals("") || this.direccion.trim().length() == 0) {
                JsfUtils.addErrorMesages("direccion", "La direccion es necesaria");
            }
            //validar cdv
            if (ciudadano.getIdCdv() == null) {
                JsfUtils.addErrorMesages("cdv", "Debe seleccionar un centro de votacion");
            }
            if (FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
                ciudadano.setDireccion(direccion);
                String fotoAnterior = ciudadano.getImg();
                if(!imagen.getSubmittedFileName().equals("")){
                    ciudadano.setImg(ciudadano.getDui() + '_' + imagen.getSubmittedFileName());
                }
                int resultado = ciudadanoModel.actualizarCiudadano(ciudadano);
                if (resultado == 1) {
                    if(!imagen.getSubmittedFileName().equals("")){
                        this.borrarImagen(fotoAnterior);
                    }
                    this.respuesta = "Ciudadano actualizado";
                    this.departamento = null;
                    this.municipio = null;
                    this.editable = false;
                    ciudadano = new CiudadanoEntity();
                    return "listaCiudadanos?faces-redirect=true";
                }
                JsfUtils.addErrorMesages(null, "No se pudo actualizar");
            } else {
                return null;
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Problemas insertando ciudadano (bean) - " + ex.toString());
            return null;
        }
    }
    
    public String desactivar(int id){
        try{
            ciudadanoModel.eliminarCiudadano(id);
            this.respuesta = "Estado cambiado";
        }catch(Exception ex){
            System.out.println("Error desacivando ciudadano (bean) - " + ex.toString());
        }
        return null;
    }
}
