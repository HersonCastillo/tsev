package sv.edu.udb.www.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.EstadoCEEntity;
import sv.edu.udb.www.entities.JRVEntity;
import sv.edu.udb.www.entities.CandidatoEntity;
import sv.edu.udb.www.entities.VotoEntity;
import sv.edu.udb.www.entities.DetalleCEEntity;
import sv.edu.udb.www.entities.DetalleUJEntity;
import sv.edu.udb.www.models.JRVModel;
import sv.edu.udb.www.models.CiudadanoModel;
import sv.edu.udb.www.models.EleccionModel;
import sv.edu.udb.www.models.TipoEstadoEleccion;
import sv.edu.udb.www.models.CandidatoModel;
import sv.edu.udb.www.models.VotoModel;
import sv.edu.udb.www.models.EstadoCEModel;
import sv.edu.udb.www.models.DetalleUJRV;

@Path("/votacion")
public class VotacionJRV {

    @EJB
    private JRVModel jrvModel;
    @EJB
    private TipoEstadoEleccion tee;
    @EJB
    private CiudadanoModel cModel;
    @EJB
    private EleccionModel eModel;
    @EJB
    private CandidatoModel caModel;
    @EJB
    private VotoModel voto;
    @EJB
    private EstadoCEModel ece;
    @EJB
    private DetalleUJRV duj;

    @GET
    @Path("jrv")
    @Produces({MediaType.APPLICATION_JSON})
    public List<JRVEntity> listarJRV() {
        return this.jrvModel.obetnerJRV();
    }

    @GET
    @Path("estados")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EstadoCEEntity> listarEstadosVotacion() {
        return this.tee.listaTipos();
    }

    @GET
    @Path("ciudadanos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CiudadanoEntity> listarCiudadanos() {
        return this.cModel.listaCiudadanos();
    }

    @GET
    @Path("ciudadano/{dui}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public CiudadanoEntity listarCiudadanoPorDUI(@PathParam("dui") String dui) {
        return this.cModel.obtenerCiudadanoPorDUI(dui);
    }

    @GET
    @Path("elecciones")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EleccionEntity> listarElecciones() {
        return this.eModel.obtenerElecciones();
    }

    @GET
    @Path("candidatos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CandidatoEntity> listarCandidatos() {
        return this.caModel.listarCandidatos();
    }

    @POST
    @Path("/{iddetalle}/{idjrv}/{idestado}/{idcandidato}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public boolean guardarVoto(
            @PathParam("iddetalle") String iddetalle,
            @PathParam("idjrv") String idjrv,
            @PathParam("idestado") String idestado,
            @PathParam("idcandidato") String idcandidato
    ) {
        try {
            int iddetalleInt = Integer.parseInt(iddetalle);
            int idjrvInt = Integer.parseInt(idjrv);
            int idestadoInt = Integer.parseInt(idestado);
            int idcandidatoInt = Integer.parseInt(idcandidato);

            DetalleCEEntity detalle = voto.obtenerDetalle(iddetalleInt);
            JRVEntity jrv = jrvModel.obtenerJRVById(idjrvInt);
            EstadoCEEntity estado = ece.obtenerEstadoCEById(idestadoInt);
            CandidatoEntity candidato = caModel.obtenerCandidato(idcandidatoInt);

            VotoEntity votoEntity = new VotoEntity();
            votoEntity.setIdCandidato(candidato);
            votoEntity.setIdJRV(jrv);
            if (idcandidatoInt > 0) {
                if (voto.crearVoto(votoEntity)) {
                    detalle.setIdEstado(estado);
                    if (voto.modificarDetalle(detalle)) {
                        return true;
                    }
                }
            } else {
                detalle.setIdEstado(estado);
                if (voto.modificarDetalle(detalle)) {
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @GET
    @Path("/presidentejrv/{dui}")
    @Produces({MediaType.APPLICATION_JSON})
    public DetalleUJEntity loginPresidente(@PathParam("dui") String dui) {
        try {
            return duj.obtenerPresidente(dui);
        } catch (Exception ex) {
            return null;
        }
    }

    @GET
    @Path("/detalle")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleCEEntity> obtenerDetalle() {
        try {
            return voto.listarDetalle();
        } catch (Exception ex) {
            return null;
        }
    }

    @GET
    @Path("/votos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VotoEntity> obtenerVotos() {
        try {
            return voto.obtenerVotos();
        } catch (Exception ex) {
            return null;
        }
    }
}
