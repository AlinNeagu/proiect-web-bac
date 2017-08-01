package proiect.bac.jackson;

import java.util.List;

public class EleviAndroidPOJO {
	
	List<ElevAndroidPOJO> elevi;
	
	public List<ElevAndroidPOJO> getElevi() {
		return elevi;
	}


	public void setElevi(List<ElevAndroidPOJO> elevi) {
		this.elevi = elevi;
	}


	@Override
	public String toString() {
		return "EleviAndroidPOJO [elevi=" + elevi + "]";
	}

}
