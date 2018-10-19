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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wecp123
 */
@Entity
@Table(name = "eleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EleccionEntity.findAll", query = "SELECT e FROM EleccionEntity e")
    , @NamedQuery(name = "EleccionEntity.findByTipo", query = "SELECT e FROM EleccionEntity e WHERE e.idTipo.id = :id")
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
    @Transient
    private boolean ingresable;
    @Transient
    private boolean ingresablee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<DetalleCEEntity> detalleCEEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<JRVEntity> jRVEntityList;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoEleccionEntity idTipo;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoEleccionEntity idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<CandidatoEntity> candidatoEntityList;

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
    public List<DetalleCEEntity> getDetalleCEEntityList() {
        return detalleCEEntityList;
    }

    public void setDetalleCEEntityList(List<DetalleCEEntity> detalleCEEntityList) {
        this.detalleCEEntityList = detalleCEEntityList;
    }

    @XmlTransient
    public List<JRVEntity> getJRVEntityList() {
        return jRVEntityList;
    }

    public void setJRVEntityList(List<JRVEntity> jRVEntityList) {
        this.jRVEntityList = jRVEntityList;
    }

    public TipoEleccionEntity getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoEleccionEntity idTipo) {
        this.idTipo = idTipo;
    }

    public EstadoEleccionEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoEleccionEntity idEstado) {
        this.idEstado = idEstado;
    }

    @XmlTransient
    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
    }

    public boolean isIngresable() {
        if (this.idTipo.getId() == 2 && this.idEstado.getId() == 1) {
            this.ingresable = true;
        }
        return ingresable;
    }

    public void setIngresable(boolean ingresable) {
        this.ingresable = ingresable;
    }

    public boolean isIngresablee() {
        if (this.idTipo.getId() == 1 && this.idEstado.getId() == 1) {
            this.ingresablee = true;
        }
        return ingresablee;
    }

    public void setIngresablee(boolean ingresablee) {
        this.ingresablee = ingresablee;
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
        return "sv.edu.udb.www.entities.EleccionEntity[ id=" + id + " ]";
    }
    
}
