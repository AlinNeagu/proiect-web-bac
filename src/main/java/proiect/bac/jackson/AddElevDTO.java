package proiect.bac.jackson;

import java.util.List;

public class AddElevDTO {
	String denumireUnitate;
	String denumireSpecializare;
	String numeElev;
	String prenumeElev;
	String initialaTata;
	List<ProbaDTO> probe;
	public String getDenumireUnitate() {
		return denumireUnitate;
	}
	public void setDenumireUnitate(String denumireUnitate) {
		this.denumireUnitate = denumireUnitate;
	}
	public String getDenumireSpecializare() {
		return denumireSpecializare;
	}
	public void setDenumireSpecializare(String denumireSpecializare) {
		this.denumireSpecializare = denumireSpecializare;
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
	public List<ProbaDTO> getProbe() {
		return probe;
	}
	public void setProbe(List<ProbaDTO> probe) {
		this.probe = probe;
	}
	
}
