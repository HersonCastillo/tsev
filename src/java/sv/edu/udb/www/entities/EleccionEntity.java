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
@Table(name = "Eleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EleccionEntity.findAll", query = "SELECT e FROM EleccionEntity e")
    , @NamedQuery(name = "EleccionEntity.findById", query = "SELECT e FROM EleccionEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EleccionEntity.findByFechIniRegistro", query = "SELECT e FROM EleccionEntity e WHERE e.fechIniRegistro = :fechIniRegistro")
    , @NamedQuery(name = "EleccionEntity.findByFechFinRegistro", query = "SELECT e FROM EleccionEntity e WHERE e.fechFinRegistro = :fechFinRegistro")
    , @NamedQuery(name = "EleccionEntity.findByFechRealizacion", query = "SELECT e FROM EleccionEntity e WHERE e.fechRealizacion = :fechRealizacion")})
public class EleccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_ini_registro")
    @Temporal(TemporalType.DATE)
    private Date fechIniRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_fin_registro")
    @Temporal(TemporalType.DATE)
    private Date fechFinRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_realizacion")
    @Temporal(TemporalType.DATE)
    private Date fechRealizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<DetalleciudadanoeleccionEntity> detalleciudadanoeleccionEntityList;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoeleccionEntity idTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<CandidatoEntity> candidatoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<JTVEntity> jTVEntityList;

    public EleccionEntity() {
    }

    public EleccionEntity(Integer id) {
        this.id = id;
    }

    public EleccionEntity(Integer id, Date fechIniRegistro, Date fechFinRegistro, Date fechRealizacion) {
        this.id = id;
        this.fechIniRegistro = fechIniRegistro;
        this.fechFinRegistro = fechFinRegistro;
        this.fechRealizacion = fechRealizacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechIniRegistro() {
        return fechIniRegistro;
    }

    public void setFechIniRegistro(Date fechIniRegistro) {
        this.fechIniRegistro = fechIniRegistro;
    }

    public Date getFechFinRegistro() {
        return fechFinRegistro;
    }

    public void setFechFinRegistro(Date fechFinRegistro) {
        this.fechFinRegistro = fechFinRegistro;
    }

    public Date getFechRealizacion() {
        return fechRealizacion;
    }

    public void setFechRealizacion(Date fechRealizacion) {
        this.fechRealizacion = fechRealizacion;
    }

    @XmlTransient
    public List<DetalleciudadanoeleccionEntity> getDetalleciudadanoeleccionEntityList() {
        return detalleciudadanoeleccionEntityList;
    }

    public void setDetalleciudadanoeleccionEntityList(List<DetalleciudadanoeleccionEntity> detalleciudadanoeleccionEntityList) {
        this.detalleciudadanoeleccionEntityList = detalleciudadanoeleccionEntityList;
    }

    public TipoeleccionEntity getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoeleccionEntity idTipo) {
        this.idTipo = idTipo;
    }

    @XmlTransient
    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
    }

    @XmlTransient
    public List<JTVEntity> getJTVEntityList() {
        return jTVEntityList;
    }

    public void setJTVEntityList(List<JTVEntity> jTVEntityList) {
        this.jTVEntityList = jTVEntityList;
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
        if (!(object instanceof EleccionEntity)) {
            return false;
        }
        EleccionEntity other = (EleccionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.EleccionEntity[ id=" + id + " ]";
    }
    
}