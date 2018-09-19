/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "Candidato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CandidatoEntity.findAll", query = "SELECT c FROM CandidatoEntity c")
    , @NamedQuery(name = "CandidatoEntity.findById", query = "SELECT c FROM CandidatoEntity c WHERE c.id = :id")})
public class CandidatoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "id_ciudano", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CiudadanoEntity idCiudano;
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EleccionEntity idEleccion;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MunicipioEntity idMunicipio;
    @JoinColumn(name = "id_partido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PartidoEntity idPartido;
    @OneToMany(mappedBy = "idCandidato")
    private List<VotoEntity> votoEntityList;

    public CandidatoEntity() {
    }

    public CandidatoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CiudadanoEntity getIdCiudano() {
        return idCiudano;
    }

    public void setIdCiudano(CiudadanoEntity idCiudano) {
        this.idCiudano = idCiudano;
    }

    public EleccionEntity getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(EleccionEntity idEleccion) {
        this.idEleccion = idEleccion;
    }

    public MunicipioEntity getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipioEntity idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public PartidoEntity getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(PartidoEntity idPartido) {
        this.idPartido = idPartido;
    }

    @XmlTransient
    public List<VotoEntity> getVotoEntityList() {
        return votoEntityList;
    }

    public void setVotoEntityList(List<VotoEntity> votoEntityList) {
        this.votoEntityList = votoEntityList;
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
        if (!(object instanceof CandidatoEntity)) {
            return false;
        }
        CandidatoEntity other = (CandidatoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.CandidatoEntity[ id=" + id + " ]";
    }
    
}
