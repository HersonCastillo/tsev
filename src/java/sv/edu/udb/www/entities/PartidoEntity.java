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
@Table(name = "Partido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidoEntity.findAll", query = "SELECT p FROM PartidoEntity p")
    , @NamedQuery(name = "PartidoEntity.findById", query = "SELECT p FROM PartidoEntity p WHERE p.id = :id")
    , @NamedQuery(name = "PartidoEntity.findByNombre", query = "SELECT p FROM PartidoEntity p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PartidoEntity.findByImg", query = "SELECT p FROM PartidoEntity p WHERE p.img = :img")})
public class PartidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String nombre;
    @Size(max = 150)
    private String img;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPartido")
    private List<CandidatoEntity> candidatoEntityList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoPartidoEntity idEstado;

    public PartidoEntity() {
    }

    public PartidoEntity(Integer id) {
        this.id = id;
    }

    public PartidoEntity(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @XmlTransient
    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
    }

    public EstadoPartidoEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoPartidoEntity idEstado) {
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
        if (!(object instanceof PartidoEntity)) {
            return false;
        }
        PartidoEntity other = (PartidoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.PartidoEntity[ id=" + id + " ]";
    }
    
}
