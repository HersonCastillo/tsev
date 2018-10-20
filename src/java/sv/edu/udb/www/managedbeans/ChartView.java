/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.PieChartModel;
import sv.edu.udb.www.entities.CDVEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.JRVEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.entities.PartidoEntity;
import sv.edu.udb.www.entities.TipoEleccionEntity;
import sv.edu.udb.www.models.CDVModel;
import sv.edu.udb.www.models.DepartamentoModel;
import sv.edu.udb.www.models.EleccionModel;
import sv.edu.udb.www.models.GraficoModel;
import sv.edu.udb.www.models.JRVModel;
import sv.edu.udb.www.models.MunicipioModel;
import sv.edu.udb.www.models.PartidoModel;
import sv.edu.udb.www.models.TipoEleccionModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author kevin
 */
@Named(value = "chartView")
@SessionScoped
public class ChartView implements Serializable {

    @EJB
    private PartidoModel partidoModel;
    @EJB
    private GraficoModel graficoModel;
    @EJB
    private JRVModel jrvModel;
    @EJB
    private CDVModel cdvModel;
    @EJB
    private MunicipioModel municipioModel;
    @EJB
    private DepartamentoModel departamentoModel;
    @EJB
    private TipoEleccionModel tipoEleccionModel;
    @EJB
    private EleccionModel eleccionModel;

    private PieChartModel pieModel2;
    private List<TipoEleccionEntity> listaTipos;
    private List<EleccionEntity> listaElecciones;
    private List<DepartamentoEntity> listaDepartamentos;
    private List<MunicipioEntity> listaMunicipios;
    private List<CDVEntity> listaCDV;
    private List<JRVEntity> listaJRV;
    private TipoEleccionEntity tipo = null;
    private DepartamentoEntity departamento = null;
    private EleccionEntity eleccion = null;
    private MunicipioEntity municipio = null;
    private CDVEntity cdv = null;
    private JRVEntity jrv = null;
    private int idEleccion = 0;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModels() {
        createPieModel2();
    }

    public void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 1);
        pieModel2.set("Brand 2", 1);
        pieModel2.set("Brand 3", 1);
        pieModel2.set("Brand 4", 1);

        pieModel2.setTitle("Votos");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(200);
        pieModel2.setShadow(false);
    }

    public List<TipoEleccionEntity> getListaTipos() {
        return tipoEleccionModel.listaTipos();
    }

    public void setListaTipos(List<TipoEleccionEntity> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List<EleccionEntity> getListaElecciones() {
        if (this.tipo == null) {
            return eleccionModel.eleccionesPorTipo(1);
        } else {
            listaElecciones = eleccionModel.eleccionesPorTipo(tipo.getId());
            if (!listaElecciones.isEmpty()) {
                this.idEleccion = listaElecciones.get(0).getId();
            } else {
                this.idEleccion = 0;
            }
            return listaElecciones;
        }
    }

    public void setListaElecciones(List<EleccionEntity> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }

    public List<DepartamentoEntity> getListaDepartamentos() {
        return departamentoModel.listaDepartamentosCombo();
    }

    public void setListaDepartamentos(List<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        if (this.departamento == null) {
            return municipioModel.listaMunicipiosPorDepartamento(1);
        } else {
            return municipioModel.listaMunicipiosPorDepartamento(this.departamento.getId());
        }
    }

    public void setListaMunicipios(List<MunicipioEntity> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<CDVEntity> getListaCDV() {
        if (this.municipio == null) {
            return cdvModel.obtenerCDVPorMunicipioCombo(0);
        } else {
            return cdvModel.obtenerCDVPorMunicipioCombo(this.municipio.getId());
        }
    }

    public void setListaCDV(List<CDVEntity> listaCDV) {
        this.listaCDV = listaCDV;
    }

    public List<JRVEntity> getListaJRV() {
        if (this.cdv == null) {
            return jrvModel.obtenerJRVPorCDV(1);
        } else {
            if (this.eleccion == null) {
                if (this.idEleccion != 0) {
                    return jrvModel.obtenerJRVPorCDV(this.cdv.getId(), this.idEleccion);
                } else {
                    return jrvModel.obtenerJRVPorCDV(1);
                }
            } else {
                return jrvModel.obtenerJRVPorCDV(this.cdv.getId(), this.eleccion.getId());
            }
        }
    }

    public void setListaJRV(List<JRVEntity> listaJRV) {
        this.listaJRV = listaJRV;
    }

    public TipoEleccionEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoEleccionEntity tipo) {
        this.tipo = tipo;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }

    public CDVEntity getCdv() {
        return cdv;
    }

    public void setCdv(CDVEntity cdv) {
        this.cdv = cdv;
    }

    public JRVEntity getJrv() {
        return jrv;
    }

    public void setJrv(JRVEntity jrv) {
        this.jrv = jrv;
    }

    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }

    public void votosGlobales() {
        try {
            if (this.eleccion != null) {
                pieModel2 = new PieChartModel();

                long nulos = graficoModel.nulosGlobales(this.eleccion.getId());
                long sv = graficoModel.sinGlobales(this.eleccion.getId());
                pieModel2.set("Nulos", nulos);
                pieModel2.set("Sin votar", sv);

                List<PartidoEntity> listaPartidos = partidoModel.listaPartidosPorEleccion(this.eleccion.getId());
                for (PartidoEntity partido : listaPartidos) {
                    long votos = graficoModel.votosGlobales(this.eleccion.getId(), partido.getId());
                    pieModel2.set(partido.getNombre(), votos);
                }

                pieModel2.setTitle("Resultados globales");
                pieModel2.setLegendPosition("e");
                pieModel2.setFill(false);
                pieModel2.setShowDataLabels(true);
                pieModel2.setDiameter(200);
                pieModel2.setShadow(false);

            } else {
                JsfUtils.addErrorMesages("eleccion", "Se debe seleccinar la eleccion");
            }
        } catch (Exception ex) {
            System.out.println("Error obteniendo los votos globales (bean) - " + ex.toString());
        }
    }

    public void votosDeartamentales() {
        try {
            if (this.eleccion != null) {
                if (this.departamento == null || this.departamento.getId() == 1) {
                    JsfUtils.addErrorMesages("departamento", "Se debe selecconar el partido");
                } else {
                    pieModel2 = new PieChartModel();

                    long nulos = graficoModel.nulosDepartamentales(this.eleccion.getId(),this.departamento.getId());
                    long sv = graficoModel.sinDepartamentales(this.eleccion.getId(),this.departamento.getId());
                    pieModel2.set("Nulos", nulos);
                    pieModel2.set("Sin votar", sv);

                    List<PartidoEntity> listaPartidos = partidoModel.listaPartidosPorEleccion(this.eleccion.getId());
                    for (PartidoEntity partido : listaPartidos) {
                        long votos = graficoModel.votosDepartamentales(this.eleccion.getId(),this.departamento.getId(), partido.getId());
                        pieModel2.set(partido.getNombre(), votos);
                    }

                    pieModel2.setTitle("Resultados departamentales");
                    pieModel2.setLegendPosition("e");
                    pieModel2.setFill(false);
                    pieModel2.setShowDataLabels(true);
                    pieModel2.setDiameter(200);
                    pieModel2.setShadow(false);

                }
            } else {
                JsfUtils.addErrorMesages("eleccion", "Se debe seleccinar la eleccion");
            }
        } catch (Exception ex) {
            System.out.println("Error obteniendo los votos globales (bean) - " + ex.toString());
        }
    }
    
    public void votosMunicipales() {
        try {
            if (this.eleccion != null) {
                if (this.departamento == null || this.departamento.getId() == 1) {
                    JsfUtils.addErrorMesages("departamento", "Se debe selecconar el partido");
                } else {
                    pieModel2 = new PieChartModel();

                    long nulos = graficoModel.nulosDepartamentales(this.eleccion.getId(),this.departamento.getId());
                    long sv = graficoModel.sinDepartamentales(this.eleccion.getId(),this.departamento.getId());
                    pieModel2.set("Nulos", nulos);
                    pieModel2.set("Sin votar", sv);

                    List<PartidoEntity> listaPartidos = partidoModel.listaPartidosPorEleccion(this.eleccion.getId());
                    for (PartidoEntity partido : listaPartidos) {
                        long votos = graficoModel.votosDepartamentales(this.eleccion.getId(),this.departamento.getId(), partido.getId());
                        pieModel2.set(partido.getNombre(), votos);
                    }

                    pieModel2.setTitle("Resultados departamentales");
                    pieModel2.setLegendPosition("e");
                    pieModel2.setFill(false);
                    pieModel2.setShowDataLabels(true);
                    pieModel2.setDiameter(200);
                    pieModel2.setShadow(false);

                }
            } else {
                JsfUtils.addErrorMesages("eleccion", "Se debe seleccinar la eleccion");
            }
        } catch (Exception ex) {
            System.out.println("Error obteniendo los votos globales (bean) - " + ex.toString());
        }
    }
}
