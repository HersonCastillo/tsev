/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Estado_partido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadopartidoEntity.findAll", query = "SELECT e FROM EstadopartidoEntity e")
    , @NamedQuery(name = "EstadopartidoEntity.findById", query = "SELECT e FROM EstadopartidoEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EstadopartidoEntity.findByDescripcion", query = "SELECT e FROM EstadopartidoEntity e WHERE e.descripcion = :descripcion")})
public class EstadopartidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String descripcion;

    public EstadopartidoEntity() {
    }

    public EstadopartidoEntity(Integer id) {
        this.id = id;
    }

    public EstadopartidoEntity(Integer id, String descripcion) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadopartidoEntity)) {
            return false;
        }
        EstadopartidoEntity other = (EstadopartidoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EstadopartidoEntity[ id=" + id + " ]";
    }
    
}
