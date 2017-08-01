package proiect.bac.export;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proiect.bac.DAO.DateElevDAO;
import proiect.bac.entities.DateElev;
import proiect.bac.entities.Proba;
import proiect.bac.jackson.ElevPOJO;
import proiect.bac.jackson.EleviPOJO;

@Component
public class EleviUTIL {
	
	
	@Autowired
	DateElevDAO dateElevDAO;
	
	public EleviPOJO getElevi() {
		EleviPOJO elevi=new EleviPOJO();
		
		ElevPOJO elev;
		
		List<ElevPOJO> eleviList=new ArrayList<ElevPOJO>();
		List<DateElev> listaElevi = new ArrayList<DateElev>();
		
		System.out.println("----Afisarea elevilor din baza de date----");
		try {
			listaElevi=dateElevDAO.findAll();
			for (int i=0; i<listaElevi.size(); i++)
			{
				System.out.println(listaElevi.get(i));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		for (DateElev dateElev : listaElevi) {
			
			elev=new ElevPOJO();
			elev.setId(dateElev.getIdElev());
			elev.setNumeElev(dateElev.getNumeElev());
			elev.setPrenumeElev(dateElev.getPrenumeElev());
			elev.setSpecializare(dateElev.getSpecializare().getDenumireSpecializare());
			elev.setLiceuElev(dateElev.getUnitate().getDenumireUnitate());
			elev.setInitialaTata(dateElev.getInitialaTata());
			
			for (Proba proba : dateElev.getProbe()) {
				
				if(proba.getIdDisciplinaSpecializare()!=null){
					if(proba.getIdDisciplinaSpecializare().getObligatoriu() == 1)
					{
						
						elev.setProbaObligatorie(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaProbaObligatorie(proba.getNota());
						elev.setNotaContestatieProbaObligatorie(proba.getNotaContestatie());
					}
					else if (proba.getIdDisciplinaSpecializare().getLimbaModerna()==1)
					{
						
						elev.setLimbaModerna(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaLimbaModerna(proba.getNota());
						
					}
					else if(proba.getIdDisciplinaSpecializare().getLimbaMaterna()==1){
					
						elev.setLimbaMaterna(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaLimbaMaterna(proba.getNota());
						elev.setNotaContestatieLimbaMaterna(proba.getNotaContestatie());
					}
					else
					{
						
						elev.setProbaAlegere(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaProbaAlegere(proba.getNota());
						elev.setNotaContestatieProbaAlegere(proba.getNotaContestatie());
					}
				}
				
			}
			eleviList.add(elev);
			System.out.println(elev.getNumeElev() + elev.getInitialaTata() + elev.getPrenumeElev() + " | " + elev.getLiceuElev() + " | " + elev.getSpecializare());
		}
		
		
		elevi.setElevi(eleviList);
		
		return elevi;
	}
	
}
