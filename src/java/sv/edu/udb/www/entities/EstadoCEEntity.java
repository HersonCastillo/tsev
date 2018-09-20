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
 * @author kevin
 */
@Entity
@Table(name = "Estado_ciudadano_eleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCEEntity.findAll", query = "SELECT e FROM EstadoCEEntity e")
    , @NamedQuery(name = "EstadoCEEntity.findById", query = "SELECT e FROM EstadoCEEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoCEEntity.findByDescripcion", query = "SELECT e FROM EstadoCEEntity e WHERE e.descripcion = :descripcion")})
public class EstadoCEEntity implements Serializable {

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
    private List<DetalleCEEntity> detalleCEEntityList;

    public EstadoCEEntity() {
    }

    public EstadoCEEntity(Integer id) {
        this.id = id;
    }

    public EstadoCEEntity(Integer id, String descripcion) {
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
    public List<DetalleCEEntity> getDetalleCEEntityList() {
        return detalleCEEntityList;
    }

    public void setDetalleCEEntityList(List<DetalleCEEntity> detalleCEEntityList) {
        this.detalleCEEntityList = detalleCEEntityList;
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
        if (!(object instanceof EstadoCEEntity)) {
            return false;
        }
        EstadoCEEntity other = (EstadoCEEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EstadoCEEntity[ id=" + id + " ]";
    }
    
}
