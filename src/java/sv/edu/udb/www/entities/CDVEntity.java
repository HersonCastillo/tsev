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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wecp123
 */
@Entity
@Table(name = "cdv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CDVEntity.findAll", query = "SELECT c FROM CDVEntity c")
    , @NamedQuery(name = "CDVEntity.findById", query = "SELECT c FROM CDVEntity c WHERE c.id = :id")
    , @NamedQuery(name = "CDVEntity.findByDireccion", query = "SELECT c FROM CDVEntity c WHERE c.direccion = :direccion")})
public class CDVEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 150)
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCdv")
    private List<JRVEntity> jRVEntityList;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MunicipioEntity idMunicipio;
    @OneToMany(mappedBy = "idCdv")
    private List<CiudadanoEntity> ciudadanoEntityList;

    public CDVEntity() {
    }

    public CDVEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<JRVEntity> getJRVEntityList() {
        return jRVEntityList;
    }

    public void setJRVEntityList(List<JRVEntity> jRVEntityList) {
        this.jRVEntityList = jRVEntityList;
    }

    public MunicipioEntity getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipioEntity idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @XmlTransient
    public List<CiudadanoEntity> getCiudadanoEntityList() {
        return ciudadanoEntityList;
    }

    public void setCiudadanoEntityList(List<CiudadanoEntity> ciudadanoEntityList) {
        this.ciudadanoEntityList = ciudadanoEntityList;
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
        if (!(object instanceof CDVEntity)) {
            return false;
        }
        CDVEntity other = (CDVEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.CDVEntity[ id=" + id + " ]";
    }
    
}
