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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Estado_ciudadano")
@NamedQueries({
    @NamedQuery(name = "EstadoCiudadanoEntity.findAll", query = "SELECT e FROM EstadoCiudadanoEntity e")
    , @NamedQuery(name = "EstadoCiudadanoEntity.findById", query = "SELECT e FROM EstadoCiudadanoEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoCiudadanoEntity.findByDescripcion", query = "SELECT e FROM EstadoCiudadanoEntity e WHERE e.descripcion = :descripcion")})
public class EstadoCiudadanoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private List<CiudadanoEntity> ciudadanoEntityList;

    public EstadoCiudadanoEntity() {
    }

    public EstadoCiudadanoEntity(Integer id) {
        this.id = id;
    }

    public EstadoCiudadanoEntity(Integer id, String descripcion) {
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
        if (!(object instanceof EstadoCiudadanoEntity)) {
            return false;
        }
        EstadoCiudadanoEntity other = (EstadoCiudadanoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EstadoCiudadanoEntity[ id=" + id + " ]";
    }
    
}
