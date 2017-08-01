package proiect.bac.entities;

import proiect.bac.entities.*;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import proiect.bac.entities.Profil;
import proiect.bac.entities.UnitateDeInvatamant;

@Entity
@Table(name = "ProfilUnitate")

@AssociationOverrides({ @AssociationOverride(name = "pk.profil", joinColumns = @JoinColumn(name = "ID_Profil")),
		@AssociationOverride(name = "pk.unitate", joinColumns = @JoinColumn(name = "ID_UnitateP")) })

public class ProfilUnitate implements Serializable {

	 @Id
	@Column(name = "ID_ProfilUnitate")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfilUnitate;

	// @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	// @JoinColumn(name = "ID_UnitateP", referencedColumnName= "ID_Unitate")
	// private UnitateDeInvatamant idUnitate;
	//
	// @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	// @JoinColumn(name = "ID_Profil", referencedColumnName= "ID_Profil")
	// private Profil idProfil;

	@Embedded
	private ProfilUnitateId pk;
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProfilUnitate == null) ? 0 : idProfilUnitate.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfilUnitate other = (ProfilUnitate) obj;
		if (idProfilUnitate == null) {
			if (other.idProfilUnitate != null)
				return false;
		} else if (!idProfilUnitate.equals(other.idProfilUnitate))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	public ProfilUnitate(){
    	this.pk=new ProfilUnitateId();
    }
	public ProfilUnitateId getPk() {
		return pk;
	}

	public void setPk(ProfilUnitateId pk) {
		this.pk = pk;
	}

	public Integer getIdProfilUnitate() {
		return idProfilUnitate;
	}

	public void setIdProfilUnitate(Integer idProfilUnitate) {
		this.idProfilUnitate = idProfilUnitate;
	}

	@Transient
	public UnitateDeInvatamant getIdUnitate() {
		return pk.getUnitate();
	}

	public void setIdUnitate(UnitateDeInvatamant idUnitate) {
		
		this.pk.setUnitate(idUnitate);
	}

	@Transient
	public Profil getIdProfil() {
		return pk.getProfil();
	}

	public void setIdProfil(Profil idProfil) {
		this.pk.setProfil(idProfil);
	}

	@Override
	public String toString() {
		return "ProfilUnitate [idProfilUnitate=" + idProfilUnitate + ", idUnitate=" + pk.getUnitate() + ", idProfil="
				+ pk.getProfil() + "]";
	}

}
