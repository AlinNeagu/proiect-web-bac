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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import example.hibernate.Main;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "DateElev")
public class DateElev {

	@Id
	@Column(name = "ID_Elev")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idElev;
	
	@Column(name = "numeElev")
	private String numeElev;
	

	@Column(name = "prenumeElev")
	private String prenumeElev;
	
	@Column(name = "initialaTata")
	private String initialaTata;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Specializare", referencedColumnName = "ID_Specializare")
	private Specializare specializare;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Unitate", referencedColumnName = "ID_Unitate")
	private UnitateDeInvatamant unitate;

	@OneToMany(mappedBy="idElev",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Proba> probe;
	
	
	
	public List<Proba> getProbe() {
		return probe;
	}

	public void setProbe(List<Proba> probe) {
		this.probe = probe;
	}

	public Integer getIdElev() {
		return idElev;
	}

	public void setIdElev(Integer idElev) {
		this.idElev = idElev;
	}

	public String getNumeElev() {
		return numeElev;
	}

	public void setNumeElev(String numeElev) {
		this.numeElev = numeElev;
	}

	public String getPrenumeElev() {
		return prenumeElev;
	}

	public void setPrenumeElev(String prenumeElev) {
		this.prenumeElev = prenumeElev;
	}

	public String getInitialaTata() {
		return initialaTata;
	}

	public void setInitialaTata(String initialaTata) {
		this.initialaTata = initialaTata;
	}

	public Specializare getSpecializare() {
		return specializare;
	}

	public void setSpecializare(Specializare specializare) {
		this.specializare = specializare;
	}

	public UnitateDeInvatamant getUnitate() {
		return unitate;
	}

	public void setUnitate(UnitateDeInvatamant unitate) {
		this.unitate = unitate;
	}

	@Override
	public String toString() {
		return "DateElev [idElev=" + idElev + ", numeElev=" + numeElev + ", prenumeElev=" + prenumeElev
				+ ", initialaTata=" + initialaTata + "]";
	}

	
	
	

}
