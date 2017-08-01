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
@Table(name="DisciplinaSpecializare")
public class DisciplinaSpecializare {
	
	@Id
	@Column(name="ID_DisciplinaSpecializare")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idDisciplinaSpecializare;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Disciplina", referencedColumnName= "ID_Disciplina")
	private Disciplina idDisciplina;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Specializare")
	private Specializare idSpecializare;

	
	@Column(name="limbaMaterna")
	private Integer limbaMaterna;
	
	@Column(name="limbaModerna")
	private Integer limbaModerna;
	
	@Column(name = "obligatoriu")
	private Integer obligatoriu;
	
	
	
	public Integer getLimbaMaterna() {
		return limbaMaterna;
	}

	public Integer getObligatoriu() {
		return obligatoriu;
	}

	public void setObligatoriu(Integer obligatoriu) {
		this.obligatoriu = obligatoriu;
	}

	public void setLimbaMaterna(Integer limbaMaterna) {
		this.limbaMaterna = limbaMaterna;
	}

	public Integer getLimbaModerna() {
		return limbaModerna;
	}

	public void setLimbaModerna(Integer limbaModerna) {
		this.limbaModerna = limbaModerna;
	}

	
	
	public List<Proba> getProbe() {
		return probe;
	}



	public void setProbe(List<Proba> probe) {
		this.probe = probe;
	}



	@OneToMany(mappedBy="idDisciplinaSpecializare",fetch=FetchType.LAZY)
    List<Proba> probe;
	
	public Integer getIdDisciplinaSpecializare() {
		return idDisciplinaSpecializare;
	}



	public void setIdDisciplinaSpecializare(Integer idDisciplinaSpecializare) {
		this.idDisciplinaSpecializare = idDisciplinaSpecializare;
	}



	public Disciplina getIdDisciplina() {
		return idDisciplina;
	}



	public void setIdDisciplina(Disciplina idDisciplina) {
		this.idDisciplina = idDisciplina;
	}



	public Specializare getIdSpecializare() {
		return idSpecializare;
	}



	public void setIdSpecializare(Specializare idSpecializare) {
		this.idSpecializare = idSpecializare;
	}



	@Override
	public String toString() {
		return "DisciplinaSpecializare [idDisciplinaSpecializare=" + idDisciplinaSpecializare + ", idDisciplina="
				+ idDisciplina + ", idSpecializare=" + idSpecializare + "]";
	}
	
	

}
