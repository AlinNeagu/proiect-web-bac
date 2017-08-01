package proiect.bac.jackson;

import java.util.List;

public class AddEleviDTO {
	
	Integer idUnitate;
	Integer idSpecializare;
	String numeElev;
	String prenumeElev;
	String initialaTata;
	List<ProbaDTO> probe;
	
	

	
	public Integer getIdSpecializare() {
		return idSpecializare;
	}
	public void setIdSpecializare(Integer idSpecializare) {
		this.idSpecializare = idSpecializare;
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
	public List<ProbaDTO> getProbe() {
		return probe;
	}
	public void setProbe(List<ProbaDTO> probe) {
		this.probe = probe;
	}

	public Integer getIdUnitate() {
		return idUnitate;
	}
	public void setIdUnitate(Integer idUnitate) {
		this.idUnitate = idUnitate;
	}
	public String getInitialaTata() {
		return initialaTata;
	}
	public void setInitialaTata(String initialaTata) {
		this.initialaTata = initialaTata;
	}
	@Override
	public String toString() {
		return "AddEleviDTO [idUnitate=" + idUnitate + ", idSpecializare=" + idSpecializare + ", numeElev=" + numeElev
				+ ", prenumeElev=" + prenumeElev + ", initialaTata=" + initialaTata + ", probe=" + probe + "]";
	}

	
	
	
}
