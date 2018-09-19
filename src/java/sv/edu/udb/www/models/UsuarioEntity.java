/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

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
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u")
    , @NamedQuery(name = "UsuarioEntity.findById", query = "SELECT u FROM UsuarioEntity u WHERE u.id = :id")
    , @NamedQuery(name = "UsuarioEntity.findByPassword", query = "SELECT u FROM UsuarioEntity u WHERE u.password = :password")})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    private String password;
    @JoinColumn(name = "id_ciudadano", referencedColumnName = "id")
    @ManyToOne
    private CiudadanoEntity idCiudadano;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipousuarioEntity idTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<DetalleusuariojrvEntity> detalleusuariojrvEntityList;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id) {
        this.id = id;
    }

    public UsuarioEntity(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CiudadanoEntity getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(CiudadanoEntity idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public TipousuarioEntity getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipousuarioEntity idTipo) {
        this.idTipo = idTipo;
    }

    @XmlTransient
    public List<DetalleusuariojrvEntity> getDetalleusuariojrvEntityList() {
        return detalleusuariojrvEntityList;
    }

    public void setDetalleusuariojrvEntityList(List<DetalleusuariojrvEntity> detalleusuariojrvEntityList) {
        this.detalleusuariojrvEntityList = detalleusuariojrvEntityList;
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
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.UsuarioEntity[ id=" + id + " ]";
    }
    
}
