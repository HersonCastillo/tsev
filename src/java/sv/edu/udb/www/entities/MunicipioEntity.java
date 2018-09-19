/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MunicipioEntity.findAll", query = "SELECT m FROM MunicipioEntity m")
    , @NamedQuery(name = "MunicipioEntity.findById", query = "SELECT m FROM MunicipioEntity m WHERE m.id = :id")
    , @NamedQuery(name = "MunicipioEntity.findByDescripcion", query = "SELECT m FROM MunicipioEntity m WHERE m.descripcion = :descripcion")})
public class MunicipioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CiudadanoEntity> ciudadanoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CandidatoEntity> candidatoEntityList;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DepartamentoEntity idDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CDVEntity> cDVEntityList;

    public MunicipioEntity() {
    }

    public MunicipioEntity(Integer id) {
        this.id = id;
    }

    public MunicipioEntity(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<CiudadanoEntity> getCiudadanoEntityList() {
        return ciudadanoEntityList;
    }

    public void setCiudadanoEntityList(List<CiudadanoEntity> ciudadanoEntityList) {
        this.ciudadanoEntityList = ciudadanoEntityList;
    }

    @XmlTransient
    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
    }

    public DepartamentoEntity getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(DepartamentoEntity idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @XmlTransient
    public List<CDVEntity> getCDVEntityList() {
        return cDVEntityList;
    }

    public void setCDVEntityList(List<CDVEntity> cDVEntityList) {
        this.cDVEntityList = cDVEntityList;
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
        if (!(object instanceof MunicipioEntity)) {
            return false;
        }
        MunicipioEntity other = (MunicipioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.MunicipioEntity[ id=" + id + " ]";
    }
    
}
