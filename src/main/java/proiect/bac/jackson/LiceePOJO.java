package proiect.bac.jackson;

import java.util.List;

public class LiceePOJO {

	List<LiceuPOJO> licee;

	public List<LiceuPOJO> getLicee() {
		return licee;
	}
	
	public void setLicee(List<LiceuPOJO> licee) {
		this.licee = licee;
	}

	@Override
	public String toString() {
		return "LiceePOJO [licee=" + licee + "]";
	}
	
	
	
}
