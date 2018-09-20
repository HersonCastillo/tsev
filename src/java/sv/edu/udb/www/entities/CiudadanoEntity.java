/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Ciudadano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CiudadanoEntity.findAll", query = "SELECT c FROM CiudadanoEntity c")
    , @NamedQuery(name = "CiudadanoEntity.findById", query = "SELECT c FROM CiudadanoEntity c WHERE c.id = :id")
    , @NamedQuery(name = "CiudadanoEntity.findByNombre", query = "SELECT c FROM CiudadanoEntity c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CiudadanoEntity.findByApellido", query = "SELECT c FROM CiudadanoEntity c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "CiudadanoEntity.findByDui", query = "SELECT c FROM CiudadanoEntity c WHERE c.dui = :dui")
    , @NamedQuery(name = "CiudadanoEntity.findByDireccion", query = "SELECT c FROM CiudadanoEntity c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CiudadanoEntity.findByImg", query = "SELECT c FROM CiudadanoEntity c WHERE c.img = :img")
    , @NamedQuery(name = "CiudadanoEntity.findByFechNac", query = "SELECT c FROM CiudadanoEntity c WHERE c.fechNac = :fechNac")
    , @NamedQuery(name = "CiudadanoEntity.findByGenero", query = "SELECT c FROM CiudadanoEntity c WHERE c.genero = :genero")})
public class CiudadanoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String img;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_nac")
    @Temporal(TemporalType.DATE)
    private Date fechNac;
    @Basic(optional = false)
    @NotNull
    private boolean genero;
    @OneToMany(mappedBy = "idCiudadano")
    private List<UsuarioEntity> usuarioEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudadano")
    private List<DetalleCEEntity> detalleCEEntityList;
    @JoinColumn(name = "id_cdv", referencedColumnName = "id")
    @ManyToOne
    private CDVEntity idCdv;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MunicipioEntity idMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudano")
    private List<CandidatoEntity> candidatoEntityList;

    public CiudadanoEntity() {
    }

    public CiudadanoEntity(Integer id) {
        this.id = id;
    }

    public CiudadanoEntity(Integer id, String nombre, String apellido, String dui, String direccion, String img, Date fechNac, boolean genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.direccion = direccion;
        this.img = img;
        this.fechNac = fechNac;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getFechNac() {
        return fechNac;
    }

    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioEntityList() {
        return usuarioEntityList;
    }

    public void setUsuarioEntityList(List<UsuarioEntity> usuarioEntityList) {
        this.usuarioEntityList = usuarioEntityList;
    }

    @XmlTransient
    public List<DetalleCEEntity> getDetalleCEEntityList() {
        return detalleCEEntityList;
    }

    public void setDetalleCEEntityList(List<DetalleCEEntity> detalleCEEntityList) {
        this.detalleCEEntityList = detalleCEEntityList;
    }

    public CDVEntity getIdCdv() {
        return idCdv;
    }

    public void setIdCdv(CDVEntity idCdv) {
        this.idCdv = idCdv;
    }

    public MunicipioEntity getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipioEntity idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @XmlTransient
    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
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
        if (!(object instanceof CiudadanoEntity)) {
            return false;
        }
        CiudadanoEntity other = (CiudadanoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.CiudadanoEntity[ id=" + id + " ]";
    }
    
}
