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

import proiect.bac.entities.Profil;

@Entity
@Table(name = "Disciplina")
public class Disciplina {

	@Id
	@Column(name = "ID_Disciplina")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idDisciplina;
	
	@Column(name = "denumireDisciplina")
	private String denumireDisciplina;

	@OneToMany(mappedBy="idDisciplina",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<DisciplinaSpecializare> discSpec;
	
	
	
	public List<DisciplinaSpecializare> getDiscSpec() {
		return discSpec;
	}

	public void setDiscSpec(List<DisciplinaSpecializare> discSpec) {
		this.discSpec = discSpec;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getDenumireDisciplina() {
		return denumireDisciplina;
	}

	public void setDenumireDisciplina(String denumireDisciplina) {
		this.denumireDisciplina = denumireDisciplina;
	}

	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", denumireDisciplina=" + denumireDisciplina + "]";
	}
}
