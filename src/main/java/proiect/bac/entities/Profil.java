package proiect.bac.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Profil")
public class Profil {
	
	@Id
	@Column(name = "ID_Profil")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProfil;
	
	
	public List<Specializare> getSpecializari() {
		return specializari;
	}
	public void setSpecializari(List<Specializare> specializari) {
		this.specializari = specializari;
	}
	
	@Column(name = "denumireProfil")
	private String denumireProfil;
	
	@OneToMany(mappedBy="idProfil",fetch=FetchType.LAZY)
    List<Specializare> specializari;
	

	
	
	

	public Integer getIdProfil() {
		return idProfil;
	}
	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}
	public String getDenumireProfil() {
		return denumireProfil;
	}
	public void setDenumireProfil(String denumireProfil) {
		this.denumireProfil = denumireProfil;
	}
	@Override
	public String toString() {
		return "Profil [idProfil=" + idProfil + ", denumireProfil=" + denumireProfil + "]";
	}
	

	
	
	
	
	
}
