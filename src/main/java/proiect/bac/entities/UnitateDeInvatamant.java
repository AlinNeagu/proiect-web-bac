package proiect.bac.entities;

import java.io.Serializable;
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
import javax.persistence.Transient;

@Entity
@Table(name="UnitateDeInvatamant")
public class UnitateDeInvatamant implements Serializable{


	private static final long serialVersionUID = 2831938580282470201L;


	@Id
	@Column(name = "ID_Unitate")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUnitate;
	
	
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Centru")
	private UnitateDeInvatamant centru;
	
	@OneToMany(mappedBy="pk.unitate",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<ProfilUnitate> profilUnitate;
	
	@OneToMany(mappedBy="unitate",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<DateElev> elevi;
	
	@Column(name = "denumireUnitate")
	private String denumireUnitate;
	
	@Column(name = "judet")
	private String judet;
	
	@OneToMany(mappedBy="pk.unitate",fetch=FetchType.EAGER)
    List<ProfilUnitate> profiluri;
	
	
	
	public Integer getIdUnitate() {
		return idUnitate;
	}
	public List<ProfilUnitate> getProfilUnitate() {
		return profilUnitate;
	}
	public void setProfilUnitate(List<ProfilUnitate> profilUnitate) {
		this.profilUnitate = profilUnitate;
	}
	public List<DateElev> getElevi() {
		return elevi;
	}
	public void setElevi(List<DateElev> elevi) {
		this.elevi = elevi;
	}
	public void setIdUnitate(Integer idUnitate) {
		this.idUnitate = idUnitate;
	}
	public UnitateDeInvatamant getCentru() {
		return centru;
	}
	public void setCentru(UnitateDeInvatamant centru) {
		this.centru = centru;
	}
	public String getDenumireUnitate() {
		return denumireUnitate;
	}
	public List<ProfilUnitate> getProfiluri() {
		return profiluri;
	}
	public void setProfiluri(List<ProfilUnitate> profiluri) {
		this.profiluri = profiluri;
	}
	public void setDenumireUnitate(String denumireUnitate) {
		this.denumireUnitate = denumireUnitate;
	}
	public String getJudet() {
		return judet;
	}
	public void setJudet(String judet) {
		this.judet = judet;
	}
	@Override
	public String toString() {
		return "UnitateDeInvatamant [idUnitate=" + idUnitate + ", centru=" + centru + ", denumireUnitate="
				+ denumireUnitate + ", judet=" + judet + "]";
	}
}
