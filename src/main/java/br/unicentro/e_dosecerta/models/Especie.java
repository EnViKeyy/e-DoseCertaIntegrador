package br.unicentro.e_dosecerta.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "especie")
@XmlRootElement
public class Especie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "especieid")
    private Integer especieId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Nome")
    private String nome;

    public Especie() {
    }

    public Especie(Integer especieId) {
        this.especieId = especieId;
    }

    public Especie(Integer especieId, String nome) {
        this.especieId = especieId;
        this.nome = nome;
    }

    public Integer getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Integer especieId) {
        this.especieId = especieId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (especieId != null ? especieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.especieId == null && other.especieId != null) || (this.especieId != null && !this.especieId.equals(other.especieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unicentro.e_dosecerta.models.Especie[ especieId=" + especieId + " ]";
    }

}
