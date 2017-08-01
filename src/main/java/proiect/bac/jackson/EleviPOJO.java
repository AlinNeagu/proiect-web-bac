package proiect.bac.jackson;

import java.util.List;

public class EleviPOJO {
	List<ElevPOJO> elevi;

	public List<ElevPOJO> getElevi() {
		return elevi;
	}
	
	public void setElevi(List<ElevPOJO> elevi) {
		this.elevi = elevi;
	}
	
	@Override
	public String toString() {
		return "EleviPOJO [elevi=" + elevi + "]";
	}
	
	
}
