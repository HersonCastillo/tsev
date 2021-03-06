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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wecp123
 */
@Entity
@Table(name = "estado_jrv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoJRVEntity.findAll", query = "SELECT e FROM EstadoJRVEntity e")
    , @NamedQuery(name = "EstadoJRVEntity.findById", query = "SELECT e FROM EstadoJRVEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoJRVEntity.findByDescripcion", query = "SELECT e FROM EstadoJRVEntity e WHERE e.descripcion = :descripcion")})
public class EstadoJRVEntity implements Serializable {

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
    private List<JRVEntity> jRVEntityList;

    public EstadoJRVEntity() {
    }

    public EstadoJRVEntity(Integer id) {
        this.id = id;
    }

    public EstadoJRVEntity(Integer id, String descripcion) {
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
    public List<JRVEntity> getJRVEntityList() {
        return jRVEntityList;
    }

    public void setJRVEntityList(List<JRVEntity> jRVEntityList) {
        this.jRVEntityList = jRVEntityList;
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
        if (!(object instanceof EstadoJRVEntity)) {
            return false;
        }
        EstadoJRVEntity other = (EstadoJRVEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EstadoJRVEntity[ id=" + id + " ]";
    }
    
}
