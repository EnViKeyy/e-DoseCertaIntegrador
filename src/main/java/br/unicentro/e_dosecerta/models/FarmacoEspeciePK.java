package br.unicentro.e_dosecerta.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FarmacoEspeciePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "especieid")
    private Integer especieId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "farmacoid")
    private Integer farmacoId;

    public FarmacoEspeciePK() {
    }

    public FarmacoEspeciePK(Integer especieId, Integer farmacoId) {
        this.especieId = especieId;
        this.farmacoId = farmacoId;
    }

    public Integer getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Integer especieId) {
        this.especieId = especieId;
    }

    public Integer getFarmacoId() {
        return farmacoId;
    }

    public void setFarmacoId(Integer farmacoId) {
        this.farmacoId = farmacoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) especieId;
        hash += (int) farmacoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FarmacoEspeciePK)) {
            return false;
        }
        FarmacoEspeciePK other = (FarmacoEspeciePK) object;
        if (this.especieId != other.especieId) {
            return false;
        }
        if (this.farmacoId != other.farmacoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unicentro.e_dosecerta.entity.FarmacoEspeciePK[ especieId=" + especieId + ", farmacoId=" + farmacoId + " ]";
    }
}
