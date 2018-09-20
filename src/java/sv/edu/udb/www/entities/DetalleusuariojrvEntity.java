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
 * @author kevin
 */
@Entity
@Table(name = "Detalle_usuario_jrv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleusuariojrvEntity.findAll", query = "SELECT d FROM DetalleusuariojrvEntity d")
    , @NamedQuery(name = "DetalleusuariojrvEntity.findById", query = "SELECT d FROM DetalleusuariojrvEntity d WHERE d.id = :id")})
public class DetalleusuariojrvEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "id_jrv", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JTVEntity idJrv;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RolEntity idRol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UsuarioEntity idUsuario;

    public DetalleusuariojrvEntity() {
    }

    public DetalleusuariojrvEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JTVEntity getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(JTVEntity idJrv) {
        this.idJrv = idJrv;
    }

    public RolEntity getIdRol() {
        return idRol;
    }

    public void setIdRol(RolEntity idRol) {
        this.idRol = idRol;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof DetalleusuariojrvEntity)) {
            return false;
        }
        DetalleusuariojrvEntity other = (DetalleusuariojrvEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.DetalleusuariojrvEntity[ id=" + id + " ]";
    }
    
}
