package br.unicentro.e_dosecerta.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dosagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dosagem.findAll", query = "SELECT d FROM Dosagem d")
    , @NamedQuery(name = "Dosagem.findByDosagemId", query = "SELECT d FROM Dosagem d WHERE d.dosagemId = :dosagemId")
    , @NamedQuery(name = "Dosagem.findByDosagem", query = "SELECT d FROM Dosagem d WHERE d.dosagem = :dosagem")
    , @NamedQuery(name = "Dosagem.findByPeso", query = "SELECT d FROM Dosagem d WHERE d.peso = :peso")
    , @NamedQuery(name = "Dosagem.findByData", query = "SELECT d FROM Dosagem d WHERE d.data = :data")
    , @NamedQuery(name = "Dosagem.findByAnimalId", query = "SELECT d FROM Dosagem d WHERE d.animalId = :animalId")
    , @NamedQuery(name = "Dosagem.findByFarmacoId", query = "SELECT d FROM Dosagem d WHERE d.farmacoId = :farmacoId")
    , @NamedQuery(name = "Dosagem.findByVeterinarioId", query = "SELECT d FROM Dosagem d WHERE d.veterinarioId = :veterinarioId")})
public class Dosagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dosagemid")
    private Integer dosagemId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Dosagem")
    private float dosagem;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Peso")
    private float peso;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Basic(optional = false)
    @NotNull
    @Column(name = "animalid")
    private int animalId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "farmacoid")
    private int farmacoId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "veterinarioid")
    private int veterinarioId;

    public Dosagem() {
    }

    public Dosagem(Integer dosagemId) {
        this.dosagemId = dosagemId;
    }

    public Dosagem(Integer dosagemId, float dosagem, float peso, Date data, int animalId, int farmacoId, int veterinarioId) {
        this.dosagemId = dosagemId;
        this.dosagem = dosagem;
        this.peso = peso;
        this.data = data;
        this.animalId = animalId;
        this.farmacoId = farmacoId;
        this.veterinarioId = veterinarioId;
    }

    public Integer getDosagemId() {
        return dosagemId;
    }

    public void setDosagemId(Integer dosagemId) {
        this.dosagemId = dosagemId;
    }

    public float getDosagem() {
        return dosagem;
    }

    public void setDosagem(float dosagem) {
        this.dosagem = dosagem;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getFarmacoId() {
        return farmacoId;
    }

    public void setFarmacoId(int farmacoId) {
        this.farmacoId = farmacoId;
    }

    public int getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dosagemId != null ? dosagemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosagem)) {
            return false;
        }
        Dosagem other = (Dosagem) object;
        if ((this.dosagemId == null && other.dosagemId != null) || (this.dosagemId != null && !this.dosagemId.equals(other.dosagemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unicentro.e_dosecerta.entity.Dosagem[ dosagemId=" + dosagemId + " ]";
    }

}
