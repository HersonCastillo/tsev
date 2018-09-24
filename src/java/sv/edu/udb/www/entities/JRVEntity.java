/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "JRV")
@NamedQueries({
    @NamedQuery(name = "JRVEntity.findAll", query = "SELECT j FROM JRVEntity j")
    , @NamedQuery(name = "JRVEntity.findById", query = "SELECT j FROM JRVEntity j WHERE j.id = :id")
    , @NamedQuery(name = "JRVEntity.findByHoraCierre", query = "SELECT j FROM JRVEntity j WHERE j.horaCierre = :horaCierre")})
public class JRVEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_cierre")
    @Temporal(TemporalType.TIME)
    private Date horaCierre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJrv")
    private List<DetalleCEEntity> detalleCEEntityList;
    @JoinColumn(name = "id_cdv", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CDVEntity idCdv;
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EleccionEntity idEleccion;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoJRVEntity idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJrv")
    private List<DetalleUJEntity> detalleUJEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJRV")
    private List<VotoEntity> votoEntityList;

    public JRVEntity() {
    }

    public JRVEntity(Integer id) {
        this.id = id;
    }

    public JRVEntity(Integer id, Date horaCierre) {
        this.id = id;
        this.horaCierre = horaCierre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public List<DetalleCEEntity> getDetalleCEEntityList() {
        return detalleCEEntityList;
    }

    public void setDetalleCEEntityList(List<DetalleCEEntity> detalleCEEntityList) {
        this.detalleCEEntityList = detalleCEEntityList;
    }

    public CDVEntity getIdCdv() {
        return idCdv;
    }

    public void setIdCdv(CDVEntity idCdv) {
        this.idCdv = idCdv;
    }

    public EleccionEntity getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(EleccionEntity idEleccion) {
        this.idEleccion = idEleccion;
    }

    public EstadoJRVEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoJRVEntity idEstado) {
        this.idEstado = idEstado;
    }

    public List<DetalleUJEntity> getDetalleUJEntityList() {
        return detalleUJEntityList;
    }

    public void setDetalleUJEntityList(List<DetalleUJEntity> detalleUJEntityList) {
        this.detalleUJEntityList = detalleUJEntityList;
    }

    public List<VotoEntity> getVotoEntityList() {
        return votoEntityList;
    }

    public void setVotoEntityList(List<VotoEntity> votoEntityList) {
        this.votoEntityList = votoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JRVEntity)) {
            return false;
        }
        JRVEntity other = (JRVEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.JRVEntity[ id=" + id + " ]";
    }
    
}
