/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.udb.www.entities.CDVEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.models.CDVModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.MunicipioModel;

/**
 *
 * @author kevin
 */
@Stateless
@Path("/combo")
public class ComboRest {

    @EJB
    private CDVModel cdvModel;
    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private DepartamentoModel departamentoModel;
    
    @GET
    @Path("/departamentos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DepartamentoEntity> listaDepartamentos(){
        return departamentoModel.listaDepartamentosCombo();
    }
    
    @GET
    @Path("/municipios/{codigo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<MunicipioEntity> listaMunicipios(@PathParam("codigo") String codigo){
        int id = Integer.parseInt(codigo);
        return municipioModel.listaMunicipiosPorDepartamento(id);
    }
    
    @GET
    @Path("/cdv/{codigo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CDVEntity> listaCDV(@PathParam("codigo") String codigo){
        int id = Integer.parseInt(codigo);
        return cdvModel.obtenerCDVPorMunicipio(id);
    }
}
