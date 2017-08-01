package proiect.bac.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProfilUnitateId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2706343855203700115L;
	@ManyToOne
	private UnitateDeInvatamant unitate;
	@ManyToOne
	private Profil profil;
	
	public UnitateDeInvatamant getUnitate() {
		return unitate;
	}
	public void setUnitate(UnitateDeInvatamant unitate) {
		this.unitate = unitate;
	}
	
	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
	
	
}
