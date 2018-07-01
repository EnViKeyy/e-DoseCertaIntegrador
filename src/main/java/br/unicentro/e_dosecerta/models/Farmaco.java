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
@Table(name = "farmaco")
@XmlRootElement
public class Farmaco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "farmacoid")
    private Integer farmacoId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Nome")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Concentracao")
    private float concentracao;

    public Farmaco() {
    }

    public Farmaco(Integer farmacoId) {
        this.farmacoId = farmacoId;
    }

    public Farmaco(Integer farmacoId, String nome, float concentracao) {
        this.farmacoId = farmacoId;
        this.nome = nome;
        this.concentracao = concentracao;
    }

    public Integer getFarmacoId() {
        return farmacoId;
    }

    public void setFarmacoId(Integer farmacoId) {
        this.farmacoId = farmacoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(float concentracao) {
        this.concentracao = concentracao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (farmacoId != null ? farmacoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Farmaco)) {
            return false;
        }
        Farmaco other = (Farmaco) object;
        if ((this.farmacoId == null && other.farmacoId != null) || (this.farmacoId != null && !this.farmacoId.equals(other.farmacoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unicentro.e_dosecerta.entity.Farmaco[ farmacoId=" + farmacoId + " ]";
    }

}
