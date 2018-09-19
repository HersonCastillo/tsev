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
@Table(name = "Voto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotoEntity.findAll", query = "SELECT v FROM VotoEntity v")
    , @NamedQuery(name = "VotoEntity.findById", query = "SELECT v FROM VotoEntity v WHERE v.id = :id")})
public class VotoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id")
    @ManyToOne
    private CandidatoEntity idCandidato;
    @JoinColumn(name = "id_JRV", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JTVEntity idJRV;

    public VotoEntity() {
    }

    public VotoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CandidatoEntity getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(CandidatoEntity idCandidato) {
        this.idCandidato = idCandidato;
    }

    public JTVEntity getIdJRV() {
        return idJRV;
    }

    public void setIdJRV(JTVEntity idJRV) {
        this.idJRV = idJRV;
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
        if (!(object instanceof VotoEntity)) {
            return false;
        }
        VotoEntity other = (VotoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.models.VotoEntity[ id=" + id + " ]";
    }
    
}
