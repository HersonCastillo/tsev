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
@Table(name = "Rol")
@NamedQueries({
    @NamedQuery(name = "RolEntity.findAll", query = "SELECT r FROM RolEntity r")
    , @NamedQuery(name = "RolEntity.findById", query = "SELECT r FROM RolEntity r WHERE r.id = :id")
    , @NamedQuery(name = "RolEntity.findByDescripcion", query = "SELECT r FROM RolEntity r WHERE r.descripcion = :descripcion")})
public class RolEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private List<DetalleUJEntity> detalleUJEntityList;

    public RolEntity() {
    }

    public RolEntity(Integer id) {
        this.id = id;
    }

    public RolEntity(Integer id, String descripcion) {
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

    public List<DetalleUJEntity> getDetalleUJEntityList() {
        return detalleUJEntityList;
    }

    public void setDetalleUJEntityList(List<DetalleUJEntity> detalleUJEntityList) {
        this.detalleUJEntityList = detalleUJEntityList;
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
        if (!(object instanceof RolEntity)) {
            return false;
        }
        RolEntity other = (RolEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.RolEntity[ id=" + id + " ]";
    }
    
}
