package br.unicentro.e_dosecerta.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "farmaco_especie")
@XmlRootElement
public class FarmacoEspecie implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected FarmacoEspeciePK farmacoEspeciePK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dose_maxima")
    private float doseMaxima;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dose_minima")
    private float doseMinima;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dose_media")
    private float doseMedia;

    public FarmacoEspecie() {
    }

    public FarmacoEspecie(FarmacoEspeciePK farmacoEspeciePK) {
        this.farmacoEspeciePK = farmacoEspeciePK;
    }

    public FarmacoEspecie(FarmacoEspeciePK farmacoEspeciePK, float doseMaxima, float doseMinima, float doseMedia) {
        this.farmacoEspeciePK = farmacoEspeciePK;
        this.doseMaxima = doseMaxima;
        this.doseMinima = doseMinima;
        this.doseMedia = doseMedia;
    }

    public FarmacoEspecie(int especieId, int farmacoId) {
        this.farmacoEspeciePK = new FarmacoEspeciePK(especieId, farmacoId);
    }

    public FarmacoEspeciePK getFarmacoEspeciePK() {
        return farmacoEspeciePK;
    }

    public void setFarmacoEspeciePK(FarmacoEspeciePK farmacoEspeciePK) {
        this.farmacoEspeciePK = farmacoEspeciePK;
    }

    public float getDoseMaxima() {
        return doseMaxima;
    }

    public void setDoseMaxima(float doseMaxima) {
        this.doseMaxima = doseMaxima;
    }

    public float getDoseMinima() {
        return doseMinima;
    }

    public void setDoseMinima(float doseMinima) {
        this.doseMinima = doseMinima;
    }

    public float getDoseMedia() {
        return doseMedia;
    }

    public void setDoseMedia(float doseMedia) {
        this.doseMedia = doseMedia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (farmacoEspeciePK != null ? farmacoEspeciePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FarmacoEspecie)) {
            return false;
        }
        FarmacoEspecie other = (FarmacoEspecie) object;
        if ((this.farmacoEspeciePK == null && other.farmacoEspeciePK != null) || (this.farmacoEspeciePK != null && !this.farmacoEspeciePK.equals(other.farmacoEspeciePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unicentro.e_dosecerta.entity.FarmacoEspecie[ farmacoEspeciePK=" + farmacoEspeciePK + " ]";
    }

}
