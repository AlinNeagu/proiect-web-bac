package proiect.bac.jackson;

public class AddProbaDTO {
	Integer id;
	String nume;
	Double nota;
	Double notaContestatie;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNume() {
		return nume;
	}



	public void setNume(String nume) {
		this.nume = nume;
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
		return "AddProbaDTO [idDiscSpec=" + id + ", nume=" + nume + ", nota=" + nota + ", notaContestatie="
				+ notaContestatie + "]";
	}
	
}
