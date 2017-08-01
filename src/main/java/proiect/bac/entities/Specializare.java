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
@Table(name="Specializare")
public class Specializare {
	
	@Id
	@Column(name = "ID_Specializare")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSpecializare;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Profil", referencedColumnName= "ID_Profil")
	private Profil idProfil;
	
	@Column(name = "denumireSpecializare")
	private String denumireSpecializare;
	
	@OneToMany(mappedBy="idSpecializare",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    List<DisciplinaSpecializare> disciplineSpecializari;
	
	@OneToMany(mappedBy="specializare",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<DateElev> elevi;
	
	
	public List<DateElev> getElevi() {
		return elevi;
	}
	public void setElevi(List<DateElev> elevi) {
		this.elevi = elevi;
	}
	public List<DisciplinaSpecializare> getDisciplineSpecializari() {
		return disciplineSpecializari;
	}
	public void setDisciplineSpecializari(List<DisciplinaSpecializare> disciplineSpecializari) {
		this.disciplineSpecializari = disciplineSpecializari;
	}
	public Integer getIdSpecializare() {
		return idSpecializare;
	}
	public void setIdSpecializare(Integer idSpecializare) {
		this.idSpecializare = idSpecializare;
	}
	public String getDenumireSpecializare() {
		return denumireSpecializare;
	}
	public void setDenumireSpecializare(String denumireSpecializare) {
		this.denumireSpecializare = denumireSpecializare;
	}
	public Profil getIdProfil() {
		return idProfil;
	}
	public void setIdProfil(Profil idProfil) {
		this.idProfil = idProfil;
	}
	@Override
	public String toString() {
		return "Specializare [idSpecializare=" + idSpecializare + ", idProfil=" + idProfil + ", denumireSpecializare="
				+ denumireSpecializare + "]";
	}
}
