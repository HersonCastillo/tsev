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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "JRV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JTVEntity.findAll", query = "SELECT j FROM JTVEntity j")
    , @NamedQuery(name = "JTVEntity.findById", query = "SELECT j FROM JTVEntity j WHERE j.id = :id")
    , @NamedQuery(name = "JTVEntity.findByHoraCierre", query = "SELECT j FROM JTVEntity j WHERE j.horaCierre = :horaCierre")})
public class JTVEntity implements Serializable {

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
    private List<DetalleciudadanoeleccionEntity> detalleciudadanoeleccionEntityList;
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
    private List<DetalleusuariojrvEntity> detalleusuariojrvEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJRV")
    private List<VotoEntity> votoEntityList;

    public JTVEntity() {
    }

    public JTVEntity(Integer id) {
        this.id = id;
    }

    public JTVEntity(Integer id, Date horaCierre) {
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

    @XmlTransient
    public List<DetalleciudadanoeleccionEntity> getDetalleciudadanoeleccionEntityList() {
        return detalleciudadanoeleccionEntityList;
    }

    public void setDetalleciudadanoeleccionEntityList(List<DetalleciudadanoeleccionEntity> detalleciudadanoeleccionEntityList) {
        this.detalleciudadanoeleccionEntityList = detalleciudadanoeleccionEntityList;
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

    @XmlTransient
    public List<DetalleusuariojrvEntity> getDetalleusuariojrvEntityList() {
        return detalleusuariojrvEntityList;
    }

    public void setDetalleusuariojrvEntityList(List<DetalleusuariojrvEntity> detalleusuariojrvEntityList) {
        this.detalleusuariojrvEntityList = detalleusuariojrvEntityList;
    }

    @XmlTransient
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
        if (!(object instanceof JTVEntity)) {
            return false;
        }
        JTVEntity other = (JTVEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.JTVEntity[ id=" + id + " ]";
    }
    
}
