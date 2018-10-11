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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wecp123
 */
@Entity
@Table(name = "detalle_ciudadano_eleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCEEntity.findAll", query = "SELECT d FROM DetalleCEEntity d")
    , @NamedQuery(name = "DetalleCEEntity.findById", query = "SELECT d FROM DetalleCEEntity d WHERE d.id = :id")})
public class DetalleCEEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "id_ciudadano", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CiudadanoEntity idCiudadano;
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EleccionEntity idEleccion;
    @JoinColumn(name = "id_jrv", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JRVEntity idJrv;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoCEEntity idEstado;

    public DetalleCEEntity() {
    }

    public DetalleCEEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CiudadanoEntity getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(CiudadanoEntity idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public EleccionEntity getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(EleccionEntity idEleccion) {
        this.idEleccion = idEleccion;
    }

    public JRVEntity getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(JRVEntity idJrv) {
        this.idJrv = idJrv;
    }

    public EstadoCEEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoCEEntity idEstado) {
        this.idEstado = idEstado;
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
        if (!(object instanceof DetalleCEEntity)) {
            return false;
        }
        DetalleCEEntity other = (DetalleCEEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.DetalleCEEntity[ id=" + id + " ]";
    }
    
}
