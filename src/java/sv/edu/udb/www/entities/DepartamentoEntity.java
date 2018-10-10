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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartamentoEntity.findAll", query = "SELECT d FROM DepartamentoEntity d")
    , @NamedQuery(name = "DepartamentoEntity.findById", query = "SELECT d FROM DepartamentoEntity d WHERE d.id = :id")
    , @NamedQuery(name = "DepartamentoEntity.findByDescripcion", query = "SELECT d FROM DepartamentoEntity d WHERE d.descripcion = :descripcion")})
public class DepartamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String descripcion;
    @Transient
    private int ciudadanos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento")
    private List<UsuarioEntity> usuarioEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento")
    private List<MunicipioEntity> municipioEntityList;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer id) {
        this.id = id;
    }

    public DepartamentoEntity(Integer id, String descripcion) {
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
    public List<UsuarioEntity> getUsuarioEntityList() {
        return usuarioEntityList;
    }

    public void setUsuarioEntityList(List<UsuarioEntity> usuarioEntityList) {
        this.usuarioEntityList = usuarioEntityList;
    }

    @XmlTransient
    public List<MunicipioEntity> getMunicipioEntityList() {
        return municipioEntityList;
    }

    public void setMunicipioEntityList(List<MunicipioEntity> municipioEntityList) {
        this.municipioEntityList = municipioEntityList;
    }

    public int getCiudadanos() {
        this.ciudadanos = 0;
        try{
            for(MunicipioEntity municipio:this.getMunicipioEntityList()){
                for(CDVEntity cdv: municipio.getCDVEntityList()){
                    for(CiudadanoEntity ciudadano:cdv.getCiudadanoEntityList()){
                        if(ciudadano.getIdEstado().getId() == 1 && ciudadano.getId() != 1){
                            ciudadanos += 1;
                        }
                    }
                }
            }
        }catch(Exception ex){
            this.ciudadanos = 0;
        }
        return ciudadanos;
    }

    public void setCiudadanos(int ciudadanos) {
        this.ciudadanos = ciudadanos;
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
        if (!(object instanceof DepartamentoEntity)) {
            return false;
        }
        DepartamentoEntity other = (DepartamentoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.DepartamentoEntity[ id=" + id + " ]";
    }
    
}
