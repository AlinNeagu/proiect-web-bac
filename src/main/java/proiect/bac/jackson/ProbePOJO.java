package proiect.bac.jackson;

import java.util.List;

public class ProbePOJO {

	
	List<ProbaDTO> probaObligatorie;
	List<ProbaDTO> probaAlegere;
	List<ProbaDTO> limbaMaterna;
	List<ProbaDTO> limbaModerna;
	
	public List<ProbaDTO> getProbaObligatorie() {
		return probaObligatorie;
	}
	public void setProbaObligatorie(List<ProbaDTO> probaObligatorie) {
		this.probaObligatorie = probaObligatorie;
	}
	public List<ProbaDTO> getProbaAlegere() {
		return probaAlegere;
	}
	public void setProbaAlegere(List<ProbaDTO> probaAlegere) {
		this.probaAlegere = probaAlegere;
	}
	public List<ProbaDTO> getLimbaMaterna() {
		return limbaMaterna;
	}
	public void setLimbaMaterna(List<ProbaDTO> limbaMaterna) {
		this.limbaMaterna = limbaMaterna;
	}
	public List<ProbaDTO> getLimbaModerna() {
		return limbaModerna;
	}
	public void setLimbaModerna(List<ProbaDTO> limbaModerna) {
		this.limbaModerna = limbaModerna;
	}
	
}
