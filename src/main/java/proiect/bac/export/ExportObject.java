package proiect.bac.export;

import java.util.ArrayList;
import java.util.List;

import proiect.bac.entities.DateElev;
import proiect.bac.entities.Disciplina;
import proiect.bac.entities.Proba;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;

public class ExportObject {

	// these objects are already inserted in db


	private DateElev elev;
	private UnitateDeInvatamant unitate;
	private Specializare specializare;
	private Proba proba;
	private Proba proba1;
	private Proba proba2;
	private Proba proba3;
	private Disciplina disciplina;
	private Disciplina disciplina1;
	private Disciplina disciplina2;
	private double medie;
	

	public UnitateDeInvatamant getUnitate() {
		return unitate;
	}

	public void setUnitate(UnitateDeInvatamant unitate) {
		this.unitate = unitate;
	}

	public Specializare getSpecializare() {
		return specializare;
	}

	public void setSpecializare(Specializare specializare) {
		this.specializare = specializare;
	}

	public Proba getProba() {
		return proba;
	}

	public void setProba(Proba proba) {
		this.proba = proba;
	}

	public Proba getProba1() {
		return proba1;
	}

	public void setProba1(Proba proba1) {
		this.proba1 = proba1;
	}

	public Proba getProba2() {
		return proba2;
	}

	public void setProba2(Proba proba2) {
		this.proba2 = proba2;
	}

	public Proba getProba3() {
		return proba3;
	}

	public void setProba3(Proba proba3) {
		this.proba3 = proba3;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Disciplina getDisciplina1() {
		return disciplina1;
	}

	public void setDisciplina1(Disciplina disciplina1) {
		this.disciplina1 = disciplina1;
	}

	public Disciplina getDisciplina2() {
		return disciplina2;
	}

	public void setDisciplina2(Disciplina disciplina2) {
		this.disciplina2 = disciplina2;
	}

	List<Proba> probe = new ArrayList<Proba>();


	public List<Proba> getProbe() {
		return probe;
	}

	public DateElev getElev() {
		return elev;
	}

	public void setElev(DateElev elev) {
		this.elev = elev;
	}

	public void setProbe(List<Proba> probe) {
		this.probe = probe;
	}

	public double getMedie() {
		return medie;
	}

	public void setMedie(double medie) {
		this.medie = medie;
	}




}
