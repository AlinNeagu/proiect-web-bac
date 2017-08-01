package proiect.bac.jackson;

import java.util.List;

public class ProfiluriPOJO {

	List<ProfilPOJO> profiluri;

	public List<ProfilPOJO> getProfiluri() {
		return profiluri;
	}
	
	public void setProfiluri(List<ProfilPOJO> profiluri) {
		this.profiluri = profiluri;
	}

	@Override
	public String toString() {
		return "ProfiluriPOJO [profiluri=" + profiluri + "]";
	}
	
	
	
}
