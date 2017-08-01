package proiect.bac.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;;

@Entity
@Table(name = "Proba")
public class Proba {

	@Id
	@Column(name = "ID_Proba")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProba;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Elev", referencedColumnName = "ID_Elev")
	private DateElev idElev;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DisciplinaSpecializare", referencedColumnName = "ID_DisciplinaSpecializare")
	private DisciplinaSpecializare idDisciplinaSpecializare;
	
	
	
	@Column(name = "nota")
	private Double nota;
	
	@Column(name = "notaContestatie")
	private Double notaContestatie;

	@Transient
	private Double notaFinala;

	public Double getNotaFinala() {
		return notaFinala;
	}

	public void setNotaFinala(Double notaFinala) {
		this.notaFinala = notaFinala;
	}
	
	public Integer getIdProba() {
		return idProba;
	}

	public void setIdProba(Integer idProba) {
		this.idProba = idProba;
	}



	public DateElev getIdElev() {
		return idElev;
	}

	public void setIdElev(DateElev idElev) {
		this.idElev = idElev;
	}

	public DisciplinaSpecializare getIdDisciplinaSpecializare() {
		return idDisciplinaSpecializare;
	}

	public void setIdDisciplinaSpecializare(DisciplinaSpecializare idDisciplinaSpecializare) {
		this.idDisciplinaSpecializare = idDisciplinaSpecializare;
	}


	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Double getNotaContestatie() {
		return notaContestatie;
	}

	public void setNotaContestatie(Double notaContestatie) {
		this.notaContestatie = notaContestatie;
	}

	@Override
	public String toString() {
		return "Proba [idProba=" + idProba + ", idElev=" + idElev + ", idDisciplinaSpecializare="
				+ idDisciplinaSpecializare + ", nota=" + nota + ", notaContestatie=" + notaContestatie + "]";
	}
	
	


	

}
