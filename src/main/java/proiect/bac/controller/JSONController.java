package proiect.bac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSInput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import proiect.bac.DAO.DateElevDAO;
import proiect.bac.DAO.DisciplinaDAO;
import proiect.bac.DAO.DisciplinaSpecializareDAO;
import proiect.bac.DAO.EMgrUtil;
import proiect.bac.DAO.ProbaDAO;
import proiect.bac.DAO.ProfilDAO;
import proiect.bac.DAO.ProfilUnitateDAO;
import proiect.bac.DAO.SpecializareDAO;
import proiect.bac.DAO.UnitateDeInvatamantDAO;
import proiect.bac.DAO.UserDAO;
import proiect.bac.entities.DateElev;
import proiect.bac.entities.Disciplina;
import proiect.bac.entities.DisciplinaSpecializare;
import proiect.bac.entities.Proba;
import proiect.bac.entities.Profil;
import proiect.bac.entities.ProfilUnitate;
import proiect.bac.entities.ProfilUnitateId;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;
import proiect.bac.entities.UserDetails;
import proiect.bac.export.ExportTara;
import proiect.bac.jackson.AddElevDTO;
import proiect.bac.jackson.AddEleviDTO;
import proiect.bac.jackson.AddProbaDTO;
import proiect.bac.jackson.DisciplinaPOJO;
import proiect.bac.jackson.DisciplinaSpecializarePOJO;
import proiect.bac.jackson.ElevAndroidPOJO;
import proiect.bac.jackson.ElevDTO;
import proiect.bac.jackson.ElevPOJO;
import proiect.bac.jackson.EleviAndroidPOJO;
import proiect.bac.jackson.EleviPOJO;
import proiect.bac.jackson.LiceePOJO;
import proiect.bac.jackson.LiceuPOJO;
import proiect.bac.jackson.ProbaDTO;
import proiect.bac.jackson.ProbaPOJO;
import proiect.bac.jackson.ProbePOJO;
import proiect.bac.jackson.ProfilPOJO;
import proiect.bac.jackson.ProfiluriPOJO;
import proiect.bac.jackson.SpecializarePOJO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

@Controller
public class JSONController {

	@Autowired
	DateElevDAO dateElevDAO;

	@Autowired
	ProbaDAO probeDAO;

	@Autowired
	ExportTara export;

	@Autowired
	SpecializareDAO specializareDAO;

	@Autowired
	ProfilDAO profilDAO;

	@Autowired
	UnitateDeInvatamantDAO liceuDAO;

	@Autowired
	DisciplinaSpecializareDAO discSpecDAO;

	@Autowired
	DisciplinaDAO disciplinaDAO;

	@Autowired
	ProfilDAO profilAfisatDAO;

	@Autowired
	ProfilUnitateDAO prfUnitateDAO;

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value="/getElevById", method = RequestMethod.GET)
	@ResponseBody
	public ElevDTO getElevById(@RequestParam(name="id")Integer id){
		
		ElevDTO elevDTO=new ElevDTO();
		DateElev elev=null;
		try {
			 elev=dateElevDAO.findById(id);
			 elevDTO.setId(elev.getIdElev());
				elevDTO.setNume(elev.getNumeElev());
				elevDTO.setInitialaTatalui(elev.getInitialaTata());
				elevDTO.setPrenume(elev.getPrenumeElev());
				
				LiceuPOJO l=new LiceuPOJO();
				l.setId(elev.getUnitate().getIdUnitate());
				l.setNume(elev.getUnitate().getDenumireUnitate());
				l.setJudet(elev.getUnitate().getDenumireUnitate());
				
				elevDTO.setLiceu(l);
				
				SpecializarePOJO s=new SpecializarePOJO();
				s.setId(elev.getSpecializare().getIdSpecializare());
				s.setNume(elev.getSpecializare().getDenumireSpecializare());
				
				elevDTO.setSpec(s);
				
				ProfilPOJO pr=new ProfilPOJO();
				pr.setIdProfil(elev.getSpecializare().getIdProfil().getIdProfil());
				pr.setDenumireProfil(elev.getSpecializare().getIdProfil().getDenumireProfil());
				
				elevDTO.setProfil(pr);
				
				ProbaDTO materna=new ProbaDTO();
				ProbaDTO moderna=new ProbaDTO();
				ProbaDTO obligatorie=new ProbaDTO();
				ProbaDTO alegere=new ProbaDTO();
				
				for (Proba proba : elev.getProbe()) {
					if(proba.getIdDisciplinaSpecializare().getLimbaMaterna()==1){
						materna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						materna.setNota(proba.getNota());
						materna.setNotaContestatie(proba.getNotaContestatie());
						materna.setProbaId(proba.getIdProba());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								materna.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								materna.setNotaFinala(proba.getNota());
							}
						}
						else{
							materna.setNota(proba.getNota());
						}
						materna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
					else if(proba.getIdDisciplinaSpecializare().getLimbaModerna()==1){
						moderna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						moderna.setNota(proba.getNota());
						moderna.setProbaId(proba.getIdProba());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								moderna.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								moderna.setNotaFinala(proba.getNota());
							}
						}
						else{
							moderna.setNota(proba.getNota());
						}
						moderna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
					else if(proba.getIdDisciplinaSpecializare().getObligatoriu()==1){
						obligatorie.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						obligatorie.setProbaId(proba.getIdProba());
						obligatorie.setNota(proba.getNota());
						obligatorie.setNotaContestatie(proba.getNotaContestatie());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								obligatorie.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								obligatorie.setNotaFinala(proba.getNota());
							}
						}
						else{
							obligatorie.setNota(proba.getNota());
						}
						
						obligatorie.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}else
					{
						alegere.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						alegere.setNota(proba.getNota());
						alegere.setProbaId(proba.getIdProba());
						alegere.setNotaContestatie(proba.getNotaContestatie());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								alegere.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								alegere.setNotaFinala(proba.getNota());
							}
						}
						else{
							alegere.setNota(proba.getNota());
						}
						
						alegere.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
				}
				elevDTO.setLimbaMaterna(materna);
				elevDTO.setLimbaModerna(moderna);
				elevDTO.setProbaObligatorie(obligatorie);
				elevDTO.setProbaAlegere(alegere);
 
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("work");
		return elevDTO;
	}
	
	
	@RequestMapping(value = "/numeElevi", method = RequestMethod.GET)
	@ResponseBody
	public EleviAndroidPOJO getNumeElevi() {
		EleviAndroidPOJO elevi = new EleviAndroidPOJO();

		ElevAndroidPOJO elev;

		List<ElevAndroidPOJO> eleviList = new ArrayList<ElevAndroidPOJO>();
		List<DateElev> listaElevi = new ArrayList<DateElev>();

		System.out.println("----Afisarea elevilor din baza de date----");
		try {
			listaElevi = dateElevDAO.findAll();
			//for (int i = 0; i < listaElevi.size(); i++) {
				//System.out.println(listaElevi.get(i));
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (DateElev dateElev : listaElevi) {

			elev = new ElevAndroidPOJO();
			elev.setId(dateElev.getIdElev());
			elev.setNumeElev(dateElev.getNumeElev());
			elev.setPrenumeElev(dateElev.getPrenumeElev());
			elev.setInitialaTata(dateElev.getInitialaTata());
			
			eleviList.add(elev);
			
		}

		elevi.setElevi(eleviList);

		return elevi;
	}




	
	
	
	@RequestMapping(value = "/elevi", method = RequestMethod.GET)
	@ResponseBody
	public EleviPOJO getElevi() {
		EleviPOJO elevi = new EleviPOJO();

		ElevPOJO elev;

		List<ElevPOJO> eleviList = new ArrayList<ElevPOJO>();
		List<DateElev> listaElevi = new ArrayList<DateElev>();

		System.out.println("----Afisarea elevilor din baza de date----");
		try {
			listaElevi = dateElevDAO.findAll();
			for (int i = 0; i < listaElevi.size(); i++) {
				System.out.println(listaElevi.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (DateElev dateElev : listaElevi) {

			elev = new ElevPOJO();
			elev.setId(dateElev.getIdElev());
			elev.setNumeElev(dateElev.getNumeElev());
			elev.setPrenumeElev(dateElev.getPrenumeElev());
			elev.setSpecializare(dateElev.getSpecializare().getDenumireSpecializare());
			elev.setLiceuElev(dateElev.getUnitate().getDenumireUnitate());
			elev.setInitialaTata(dateElev.getInitialaTata());

			for (Proba proba : dateElev.getProbe()) {

				if (proba.getIdDisciplinaSpecializare() != null) {
					if (proba.getIdDisciplinaSpecializare().getObligatoriu() == 1) {

						elev.setProbaObligatorie(
								proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaProbaObligatorie(proba.getNota());
						elev.setNotaContestatieProbaObligatorie(proba.getNotaContestatie());
					} else if (proba.getIdDisciplinaSpecializare().getLimbaModerna() == 1) {

						elev.setLimbaModerna(
								proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaLimbaModerna(proba.getNota());

					} else if (proba.getIdDisciplinaSpecializare().getLimbaMaterna() == 1) {

						elev.setLimbaMaterna(
								proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaLimbaMaterna(proba.getNota());
						elev.setNotaContestatieLimbaMaterna(proba.getNotaContestatie());
					} else {

						elev.setProbaAlegere(
								proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
						elev.setNotaProbaAlegere(proba.getNota());
						elev.setNotaContestatieProbaAlegere(proba.getNotaContestatie());
					}
				}

			}
			eleviList.add(elev);
			System.out.println(elev.getNumeElev() + elev.getInitialaTata() + elev.getPrenumeElev() + " | "
					+ elev.getLiceuElev() + " | " + elev.getSpecializare());
		}

		elevi.setElevi(eleviList);

		return elevi;
	}

	// metoda pt export
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getExport() {

		try {
			return export.exportPeTara();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	// metoda de afisat licee
		@RequestMapping(value = "/numeLicee", method = RequestMethod.GET)
		@ResponseBody
		public LiceePOJO getnumeLicee() {

			LiceePOJO licee = new LiceePOJO();
			LiceuPOJO liceu;

			List<LiceuPOJO> liceeList = new ArrayList<LiceuPOJO>();
			List<UnitateDeInvatamant> listaLicee = null;

			try {
				listaLicee = liceuDAO.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("#################################################################");
			for (UnitateDeInvatamant dateLicee : listaLicee) {

				
				liceu = new LiceuPOJO();
				System.out.println(dateLicee.getDenumireUnitate());
				liceu.setId(dateLicee.getIdUnitate());
				liceu.setNume(dateLicee.getDenumireUnitate());
				

				

				liceeList.add(liceu);
			}
			System.out.println("#################################################################");
			licee.setLicee(liceeList);

			return licee;
		}
	
	

	// metoda de afisat licee
	@RequestMapping(value = "/licee", method = RequestMethod.GET)
	@ResponseBody
	public LiceePOJO getLicee() {

		LiceePOJO licee = new LiceePOJO();
		LiceuPOJO liceu;

		List<LiceuPOJO> liceeList = new ArrayList<LiceuPOJO>();
		List<UnitateDeInvatamant> listaLicee = null;

		try {
			listaLicee = liceuDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("#################################################################");
		for (UnitateDeInvatamant dateLicee : listaLicee) {

			List<ProfilPOJO> profilPOJOs = new ArrayList<ProfilPOJO>();
			liceu = new LiceuPOJO();
			System.out.println(dateLicee.getDenumireUnitate());
			liceu.setId(dateLicee.getIdUnitate());
			liceu.setNume(dateLicee.getDenumireUnitate());
			liceu.setJudet(dateLicee.getJudet());

			for (ProfilUnitate profil : dateLicee.getProfiluri()) {
				ProfilPOJO profilPOJO = new ProfilPOJO();
				profilPOJO.setDenumireProfil(profil.getIdProfil().getDenumireProfil());

				profilPOJO.setIdProfil(profil.getIdProfil().getIdProfil());
				profilPOJOs.add(profilPOJO);
			}

			liceu.setIdProfil(profilPOJOs);
			System.out.println(
					liceu.getId() + " " + liceu.getNume() + " " + liceu.getJudet() + "  " + liceu.getIdProfil());

			liceeList.add(liceu);
		}
		System.out.println("#################################################################");
		licee.setLicee(liceeList);

		return licee;
	}

	// metoda pentru adaugarea disciplinei

	@RequestMapping(value = "/disciplina", method = RequestMethod.GET)
	@ResponseBody

	public Boolean insertDisciplina(@RequestParam(name = "disciplina") String disciplina) {
		System.out.println(disciplina);
		try {
			Disciplina d = new Disciplina();
			d.setDenumireDisciplina(disciplina);
			DisciplinaDAO dao = new DisciplinaDAO();
			dao.createDisciplina(d);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
/*
	// metoda pentru adaugarea specializare pentru un profil existent
	@RequestMapping(value = "/specializare", method = RequestMethod.GET)
	@ResponseBody

	public Boolean insertSpecializare(@RequestParam(name = "specializare") String specializare,
			@RequestParam(name = "idProfil") Integer idProfil) {
		try {
			Specializare s = new Specializare();
			s.setDenumireSpecializare(specializare);
			Profil profil;
			profil = profilDAO.findProfilById(idProfil);
			s.setIdProfil(profil);
			specializareDAO.createSpecializare(s);
			System.out.println("s-a adaugat specializarea : "+s);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
*/
	@RequestMapping(value="/specializare", method = RequestMethod.GET)
	@ResponseBody
	
	public Boolean insertSpecializare(@RequestParam(name = "specializare") String specializare, @RequestParam(name = "idProfil") Integer idProfil){
		try {
			Specializare s = new Specializare();
			s.setDenumireSpecializare(specializare);
			Profil profil ;
			profil=profilDAO.findProfilById(idProfil);
			s.setIdProfil(profil);
			specializareDAO.createSpecializare(s);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	
	// metoda pentru editare specializare
	@RequestMapping(value = "/editSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public Boolean editSpecializare(@RequestParam(name = "specializare") String specializare)
			throws JsonParseException, JsonMappingException, IOException {
		// System.out.println(specializare);
		SpecializarePOJO specPojo = new SpecializarePOJO();

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("Spec ce va fi editata:  " + specializare);
		try{
			
		
		specPojo = mapper.readValue(specializare, SpecializarePOJO.class);
		Specializare specc = specializareDAO.findSpecializareById(specPojo.getId());// ob
																					// adus
																					// din
																					// db
		Profil profil = profilDAO.findProfilById(specPojo.getProfil().getIdProfil());
		specc.setIdSpecializare(specPojo.getId());
		specc.setDenumireSpecializare(specPojo.getNume());
		specc.setIdProfil(profil);
		specializareDAO.updateSpecializare(specc);
		System.out.println("Specializarea updatata:   " + specc);
		}
		catch(Exception e ){
			return false;
		}
		return true;
		
		

	}

	// metoda pentru adaugarea liceului

	@RequestMapping(value = "/liceu", method = RequestMethod.GET)
	@ResponseBody
	// @JsonIgnoreProperties(ignoreUnknown = true)
	public Boolean insertLiceu(@RequestParam(name = "liceu") String liceu) {
		System.out.println(liceu);
		LiceuPOJO liceuPojo = new LiceuPOJO();

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("########################################");
			liceuPojo = mapper.readValue(liceu, LiceuPOJO.class);
			UnitateDeInvatamant unitate = new UnitateDeInvatamant();

			List<ProfilUnitate> listaProfilUnitate = new ArrayList<>();
			unitate.setDenumireUnitate(liceuPojo.getNume());
			unitate.setJudet(liceuPojo.getJudet());
			unitate.setCentru(null);
			liceuDAO.insertLiceu(unitate);
			System.out.println(unitate);

			for (int i = 0; i < liceuPojo.getIdProfilInt().size(); i++) {
				ProfilUnitate prfUnitate = new ProfilUnitate();
				// Profil p = new Profil();
				Profil p = profilDAO.findProfilById(liceuPojo.getIdProfilInt().get(i));
				// p.setIdProfil(liceuPojo.getIdProfilInt().get(i));
				// prfUnitate.setIdProfil(p);
				ProfilUnitateId pk = new ProfilUnitateId();
				pk.setProfil(p);
				pk.setUnitate(unitate);
				prfUnitate.setPk(pk);
				prfUnitateDAO.createProfilUnitate(prfUnitate);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// EDIT liceu

	@RequestMapping(value = "/editLiceu", method = RequestMethod.GET)
	@ResponseBody
	// @JsonIgnoreProperties(ignoreUnknown = true)
	public Boolean editLiceu(@RequestParam(name = "liceu") String liceu) {
		System.out.println(liceu);
		LiceuPOJO liceuPojo = new LiceuPOJO();

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("########################################");
			liceuPojo = mapper.readValue(liceu, LiceuPOJO.class);
			UnitateDeInvatamant unitate = liceuDAO.findUnitateDeInvatamantById(liceuPojo.getId());// ob
																									// adus
																									// din
																									// db

			unitate.setDenumireUnitate(liceuPojo.getNume());
			unitate.setIdUnitate(liceuPojo.getId());
			unitate.setJudet(liceuPojo.getJudet());
			unitate.setCentru(null);
			liceuDAO.updateUnitateDeInvatamant(unitate);

			System.out.println(unitate);

			EntityManager em = EMgrUtil.createEntityManager();

			List<ProfilUnitate> profilUnitates = unitate.getProfiluri();

			System.out.println("List of ProfilUnitate");
			for (ProfilUnitate profil : profilUnitates) {
				System.out.println(profil.getPk().getProfil().getIdProfil());
			}

			while (profilUnitates.size() > 0) {
				System.out.println("Size: " + profilUnitates.size());
				System.out.println("Current pu: " + profilUnitates.get(0).getPk().getProfil().getIdProfil());
				Profil profil = profilUnitates.get(0).getPk().getProfil();
				profilUnitates.remove(0);

				prfUnitateDAO.removeProfilUnitate(profil.getIdProfil(), unitate.getIdUnitate());
			}

			for (int j = 0; j < liceuPojo.getIdProfil().size(); j++) {
				em.getTransaction().begin();

				Profil profil = profilDAO.findProfilById(liceuPojo.getIdProfil().get(j).getIdProfil());

				ProfilUnitate pu = new ProfilUnitate();

				ProfilUnitateId pui = new ProfilUnitateId();
				pui.setProfil(profil);
				pui.setUnitate(unitate);

				pu.setPk(pui);
				em.persist(pu);
				em.flush();
				em.getTransaction().commit();

				System.out.println("S-a adaugat profilul " + profil.getDenumireProfil());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// metoda pentru inserare profil nou

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	@ResponseBody

	public Boolean insertProfil(@RequestParam(name = "profil") String profil) {
		System.out.println(profil);
		try {
			Profil d = new Profil();
			d.setDenumireProfil(profil);
			ProfilDAO profilDao = new ProfilDAO();
			profilDao.createProfil(d);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// metoda pentru afisat profiluri
	@RequestMapping(value = "/profiluri", method = RequestMethod.GET)
	@ResponseBody
	public ProfiluriPOJO getProfiluri() {

		ProfiluriPOJO profiluri = new ProfiluriPOJO();

		List<ProfilPOJO> profiluriList = new ArrayList<ProfilPOJO>();
		List<Profil> listaProfiluri = new ArrayList<Profil>();
		// List<Profil> listaProfiluri = null;

		System.out.println("hello2");

		try {
			listaProfiluri = profilAfisatDAO.findAll();
			System.out.println(listaProfiluri);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Profil dateProfiluri : listaProfiluri) {

			ProfilPOJO profil = new ProfilPOJO();

			profil.setIdProfil(dateProfiluri.getIdProfil());
			profil.setDenumireProfil(dateProfiluri.getDenumireProfil());
			profiluriList.add(profil);

			System.out.println(profil.getIdProfil() + " " + profil.getDenumireProfil());

		}

		profiluri.setProfiluri(profiluriList);

		return profiluri;
	}

	
	@RequestMapping(value = "/getMaternaForSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public ProbePOJO getMaternaForSpecializare(@RequestParam(name = "specializare") String numeSpecializare) {
		ProbePOJO probe = new ProbePOJO();
		Specializare spec = specializareDAO.getSpecializareByName(numeSpecializare);
		List<ProbaDTO> limbaMaterna=new ArrayList<>();
		for ( DisciplinaSpecializare discSpec: spec.getDisciplineSpecializari()) {
			if (discSpec.getLimbaMaterna()==1){
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				limbaMaterna.add(proba);
			}
		}
		probe.setLimbaMaterna(limbaMaterna);
		return probe;
	}
	@RequestMapping(value = "/getObligatoriuForSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public ProbePOJO getObligatoriuForSpecializare(@RequestParam(name = "specializare") String numeSpecializare) {
		ProbePOJO probe = new ProbePOJO();
		Specializare spec = specializareDAO.getSpecializareByName(numeSpecializare);
		List<ProbaDTO> obligatoriu=new ArrayList<>();
		for ( DisciplinaSpecializare discSpec: spec.getDisciplineSpecializari()) {
			if(discSpec.getObligatoriu()==1)
			{
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				obligatoriu.add(proba);
			}
		}
		probe.setProbaObligatorie(obligatoriu);
		return probe;
	}
	
	@RequestMapping(value = "/getAlegereForSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public ProbePOJO getAlegereForSpecializare(@RequestParam(name = "specializare") String numeSpecializare) {
		ProbePOJO probe = new ProbePOJO();
		Specializare spec = specializareDAO.getSpecializareByName(numeSpecializare);
		List<ProbaDTO> alegere=new ArrayList<>();
		for ( DisciplinaSpecializare discSpec: spec.getDisciplineSpecializari()) {
			ProbaDTO proba=new ProbaDTO();
			proba.setId(discSpec.getIdDisciplinaSpecializare());
			System.out.println(discSpec.getIdDisciplinaSpecializare());
			proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
			alegere.add(proba);
		}
		probe.setProbaAlegere(alegere);
		return probe;
	}
	
	@RequestMapping(value = "/getModernaForSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public ProbePOJO getModernaForSpecializare(@RequestParam(name = "specializare") String numeSpecializare) {
		ProbePOJO probe = new ProbePOJO();
		Specializare spec = specializareDAO.getSpecializareByName(numeSpecializare);
		List<ProbaDTO> limbaModerna=new ArrayList<>();
		for ( DisciplinaSpecializare discSpec: spec.getDisciplineSpecializari()) {
			if(discSpec.getLimbaModerna()==1){
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				System.out.println(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				limbaModerna.add(proba);
			}
		}
		probe.setLimbaModerna(limbaModerna);
		return probe;
	}
	
	@RequestMapping(value = "/getProfiluriForUnitate", method = RequestMethod.GET)
	@ResponseBody
	public List<ProfilPOJO> getProfiluriForUnitate(@RequestParam(name = "unitate") String unitate) {
		System.out.println(unitate);
		List<ProfilPOJO> profiluri = new ArrayList<ProfilPOJO>();

		UnitateDeInvatamant liceu = liceuDAO.getUnitateByName(unitate);

		for (ProfilUnitate profilUnitate : liceu.getProfiluri()) {
			ProfilPOJO profil = new ProfilPOJO();
			profil.setIdProfil(profilUnitate.getIdProfil().getIdProfil());
			profil.setDenumireProfil(profilUnitate.getIdProfil().getDenumireProfil());
			profiluri.add(profil);
		}

		return profiluri;
	}
	
	
	
	@RequestMapping(value = "/getProfiluriForUnitateTrial", method = RequestMethod.GET)
	@ResponseBody
	public List<ProfilPOJO> getProfiluriForUnitateTrial(@RequestParam(name = "unitate") String unitate) {
		System.out.println(unitate);
		List<ProfilPOJO> profiluriLista = new ArrayList<ProfilPOJO>();

		UnitateDeInvatamant liceu = liceuDAO.getUnitateByName(unitate);
		ProfiluriPOJO profiluriPOJO = new ProfiluriPOJO() ;
		for (ProfilUnitate profilUnitate : liceu.getProfiluri()) {
			ProfilPOJO profil = new ProfilPOJO();
			profil.setIdProfil(profilUnitate.getIdProfil().getIdProfil());
			profil.setDenumireProfil(profilUnitate.getIdProfil().getDenumireProfil());
			profiluriLista.add(profil);
		}
		profiluriPOJO.setProfiluri(profiluriLista);
		return (List<ProfilPOJO>) profiluriPOJO;
	}
	
	
	
	

	@RequestMapping(value = "/getSpecializariForProfil", method = RequestMethod.GET)
	@ResponseBody
	public List<SpecializarePOJO> getSpecializariForProfil(@RequestParam(name = "profil") String numeProfil) {
		List<SpecializarePOJO> specializari = new ArrayList<>();

		for (Specializare specializare : profilDAO.getProfilByName(numeProfil).getSpecializari()) {
			SpecializarePOJO spec = new SpecializarePOJO();
			ProfilPOJO profil = new ProfilPOJO();
			profil.setIdProfil(specializare.getIdProfil().getIdProfil());
			spec.setId(specializare.getIdSpecializare());
			spec.setNume(specializare.getDenumireSpecializare());
			spec.setProfil(profil);
			specializari.add(spec);
		}

		return specializari;
	}
	/*
	 * //get Profil For a Specified Specializare
	 * 
	 * @RequestMapping(value = "/getProfilForSpecializare", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public ProfilPOJO
	 * getProfilForSpecializare(@RequestParam(name = "spec") String
	 * numeSpecializare) { ProfilPOJO profil = new ProfilPOJO(); Specializare
	 * specializare =specializareDAO.getSpecializareByName(numeSpecializare);
	 * System.out.println("Specializare DAO "+specializare);
	 * 
	 * if(specializare.getIdProfil().getIdProfil() == profil.getCurrentId()){ //
	 * return profilDAO.getProfilByName(profil.getIdProfil());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * return null; }
	 */
	


	

	

	
	
	
	@RequestMapping(value = "/getProbeForSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public ProbePOJO getProbeForSpecializare(@RequestParam(name = "specializare") String numeSpecializare) {
		ProbePOJO probe = new ProbePOJO();
		Specializare spec = specializareDAO.getSpecializareByName(numeSpecializare);
	
		List<ProbaDTO> obligatoriu=new ArrayList<>();
		List<ProbaDTO> alegere=new ArrayList<>();
		List<ProbaDTO> limbaModerna=new ArrayList<>();
		List<ProbaDTO> limbaMaterna=new ArrayList<>();
		
		for ( DisciplinaSpecializare discSpec: spec.getDisciplineSpecializari()) {
			if(discSpec.getObligatoriu()==1)
			{
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				obligatoriu.add(proba);
			}
			else if (discSpec.getLimbaMaterna()==1){
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				limbaMaterna.add(proba);
			}
			else if(discSpec.getLimbaModerna()==1){
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				System.out.println(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				limbaModerna.add(proba);
			}
			else {
				ProbaDTO proba=new ProbaDTO();
				proba.setId(discSpec.getIdDisciplinaSpecializare());
				System.out.println(discSpec.getIdDisciplinaSpecializare());
				proba.setNume(discSpec.getIdDisciplina().getDenumireDisciplina());
				alegere.add(proba);
			}
		}

		probe.setLimbaMaterna(limbaMaterna);
		probe.setLimbaModerna(limbaModerna);
		probe.setProbaAlegere(alegere);
		probe.setProbaObligatorie(obligatoriu);
		return probe;
	}
	@RequestMapping(value = "/insertElev", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertElev(@RequestParam(name="elev")String elevJSON){
		
		System.out.println("###################INSERT#####################");
		System.out.println(elevJSON);
		AddElevDTO elev=new AddElevDTO();
		DateElev elevNou=new DateElev();
		Specializare spec=new Specializare();
		UnitateDeInvatamant unitate=new UnitateDeInvatamant();
		ObjectMapper om=new ObjectMapper();
		List<Proba> probe=new ArrayList<>();
		try {
			elev = om.readValue(elevJSON, AddElevDTO.class);
			elevNou.setInitialaTata(elev.getInitialaTata());
			System.out.println("initiala: "+elevNou.getInitialaTata());
			elevNou.setNumeElev(elev.getNumeElev());
			System.out.println("numee: "+elevNou.getNumeElev());
			elevNou.setPrenumeElev(elev.getPrenumeElev());
			//o sa pun getidbyName(elev.getDenumireSpecializare)
			
			spec = specializareDAO.getIdByName(elev.getDenumireSpecializare());
		
			unitate = liceuDAO.getUnitateByName(elev.getDenumireUnitate());
			elevNou.setUnitate(unitate);
			elevNou.setSpecializare(spec);

			elevNou.setProbe(probe);

			dateElevDAO.createDateElev(elevNou);

			for (ProbaDTO probaDTO : elev.getProbe()) {
				Proba proba = new Proba();
				DisciplinaSpecializare discSpec = new DisciplinaSpecializare();
				Disciplina discNume = disciplinaDAO.getIdByName(probaDTO.getNume());
				System.out.println("Care e id-ul ala " +discNume.getIdDisciplina());
				
				//discSpec = discSpecDAO.getIdByName(discNume.getIdDisciplina());
				System.out.println("Id disciplina este: "+discNume.getIdDisciplina());
				System.out.println("Id specializare este: "+spec.getIdSpecializare());
				discSpec = discSpecDAO.getIdByName(discNume.getIdDisciplina(), spec.getIdSpecializare());
				System.out.println("who is discSpec? "+discSpec);
				//System.out.println("Id-ul este: "+discSpec.getIdDisciplinaSpecializare());
				proba.setIdDisciplinaSpecializare(discSpec);

				proba.setNota(probaDTO.getNota());
				proba.setNotaContestatie(probaDTO.getNotaContestatie());
				proba.setIdElev(elevNou);
				probeDAO.createProba(proba);
				System.out.println("Am adaugat o proba");
				System.out.println("proba este:" +proba);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	

	@RequestMapping(value = "/addElev", method = RequestMethod.GET)
	@ResponseBody
	public boolean addElev(@RequestParam(name="elev")String elevJSON){
		
		System.out.println("###################INSERT#####################");
		System.out.println(elevJSON);
		AddEleviDTO elev=new AddEleviDTO();
		DateElev elevNou=new DateElev();
		Specializare spec=new Specializare();
		UnitateDeInvatamant unitate=new UnitateDeInvatamant();
		ObjectMapper om=new ObjectMapper();
		List<Proba> probe=new ArrayList<>();
		try {
			elev = om.readValue(elevJSON, AddEleviDTO.class);
			elevNou.setInitialaTata(elev.getInitialaTata());
			elevNou.setNumeElev(elev.getNumeElev());
			elevNou.setPrenumeElev(elev.getPrenumeElev());
			//o sa pun getidbyName(elev.getDenumireSpecializare)
			spec = specializareDAO.findSpecializareById(elev.getIdSpecializare());
			
			unitate = liceuDAO.findUnitateDeInvatamantById(elev.getIdUnitate());
			elevNou.setUnitate(unitate);
			elevNou.setSpecializare(spec);

			elevNou.setProbe(probe);

			dateElevDAO.createDateElev(elevNou);

			for (ProbaDTO probaDTO : elev.getProbe()) {
				Proba proba = new Proba();
				DisciplinaSpecializare discSpec = new DisciplinaSpecializare();
				discSpec = discSpecDAO.findDisciplinaSpecializareById(probaDTO.getId());
				proba.setIdDisciplinaSpecializare(discSpec);

				proba.setNota(probaDTO.getNota());
				proba.setNotaContestatie(probaDTO.getNotaContestatie());
				proba.setIdElev(elevNou);
				probeDAO.createProba(proba);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/getSpecializari", method = RequestMethod.GET)
	@ResponseBody
	public List<SpecializarePOJO> getSpecializari() {
		List<Specializare> specs = specializareDAO.findAll();

		List<SpecializarePOJO> response = new ArrayList<>();

		for (Specializare s : specs) {
			SpecializarePOJO specPOJO = new SpecializarePOJO();
			specPOJO.setId(s.getIdSpecializare());
			specPOJO.setNume(s.getDenumireSpecializare());

			response.add(specPOJO);
		}

		return response;
	}

	@RequestMapping(value = "/getDiscipline", method = RequestMethod.GET)
	@ResponseBody
	public List<DisciplinaPOJO> getDiscipline() {
		List<Disciplina> discs = disciplinaDAO.findAll();

		List<DisciplinaPOJO> response = new ArrayList<>();

		for (Disciplina s : discs) {
			DisciplinaPOJO discPOJO = new DisciplinaPOJO();
			discPOJO.setId(s.getIdDisciplina());
			discPOJO.setNume(s.getDenumireDisciplina());

			response.add(discPOJO);
		}

		return response;
	}
	
	@RequestMapping(value="/checkUser", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkElevi(@RequestParam("user") String username, @RequestParam("pass") String pass) {
		UserDetails userDetails;
		Boolean response = false;
		System.out.println(username + " " + pass);
		userDetails = userDAO.getUserByPasswordAndName(username, pass);
		if (userDetails.getUsername().equals(username) && userDetails.getPassword().equals(pass))
			return true;
		return response;
	}

	@RequestMapping(value = "/addProba", method = RequestMethod.GET)
	@ResponseBody
	public boolean addProba(@RequestParam(name = "proba") String proba) {
		System.out.println(proba);
		ObjectMapper om = new ObjectMapper();
		DisciplinaSpecializarePOJO discSpecPOJO;
		DisciplinaSpecializare discSpec = new DisciplinaSpecializare();

		try {
			discSpecPOJO = om.readValue(proba, DisciplinaSpecializarePOJO.class);

			discSpec.setLimbaMaterna(discSpecPOJO.isMaterna() ? 1 : 0);
			discSpec.setLimbaModerna(discSpecPOJO.isModerna() ? 1 : 0);
			discSpec.setObligatoriu(discSpecPOJO.isObligatorie() ? 1 : 0);

			Disciplina disc = disciplinaDAO.findDisciplinaById(discSpecPOJO.getIdDisc());
			Specializare spec = specializareDAO.findSpecializareById(discSpecPOJO.getIdSpec());

			discSpec.setIdDisciplina(disc);
			discSpec.setIdSpecializare(spec);

			discSpecDAO.createDisciplinaSpecializare(discSpec);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/getProfiluri", method = RequestMethod.GET)
	@ResponseBody
	public List<ProfilPOJO> getProfiluir() {

		List<Profil> profiluri = profilDAO.findAll();
		List<ProfilPOJO> response = new ArrayList<>();

		for (Profil profil : profiluri) {
			ProfilPOJO profilPOJO = new ProfilPOJO();
			profilPOJO.setDenumireProfil(profil.getDenumireProfil());
			profilPOJO.setIdProfil(profil.getIdProfil());
			response.add(profilPOJO);
		}

		return response;
	}

	@RequestMapping(value = "/getProbe", method = RequestMethod.GET)
	@ResponseBody
	public List<ProbaPOJO> getProbe() {

		List<DisciplinaSpecializare> probe = discSpecDAO.findAll();
		List<ProbaPOJO> response = new ArrayList<>();

		for (DisciplinaSpecializare proba : probe) {
			ProbaPOJO probaPojo = new ProbaPOJO();
			probaPojo.setId(proba.getIdDisciplinaSpecializare());
			probaPojo.setNume(proba.getIdDisciplina().getDenumireDisciplina());
		}

		return null;
	}

	@RequestMapping(value = "/deleteElev", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteElev(@RequestParam(name = "idElev") Integer idElev) {
		System.out.println(idElev);

		try {
			dateElevDAO.deleteById(idElev);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("done");
		return true;
	}
	
	@RequestMapping(value="/deleteLiceu", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteLiceu(@RequestParam(name="id")Integer id){
		System.out.println(id);
		
		try {
			liceuDAO.deleteUnitateDeInvatamantById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("done");
		return true;
	}
	
	@RequestMapping(value="/deleteSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteSpecializare(@RequestParam(name="id")Integer id){
		System.out.println(id);
		
		try {
			specializareDAO.deleteSpecializareById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("done");
		return true;
	}
	
	@RequestMapping(value="/deleteProba", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteProba(@RequestParam(name = "idProba") Integer idProba) {
		try {
			discSpecDAO.deleteDisciplinaSpecializareById(idProba);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/deleteProfil", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteProfil(@RequestParam(name="id")Integer idProfil){
		try {
			profilDAO.deleteProfilById(idProfil);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(value="/deleteDisciplina", method = RequestMethod.GET)
	@ResponseBody
	public Boolean deleteDisciplina(@RequestParam(name="id")Integer id){
		try {
			disciplinaDAO.deleteDisciplinaById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(value="/updateProfil", method = RequestMethod.GET)
	@ResponseBody
	public Boolean updateProfil(@RequestParam(name = "idProfil") Integer idProfil,
			@RequestParam(name = "nume") String nume) {
		Profil profil;
		try {
			profil = profilDAO.findProfilById(idProfil);
			profil.setDenumireProfil(nume);
			profilDAO.updateProfil(profil);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/updateLiceu", method = RequestMethod.GET)
	@ResponseBody
	public Boolean updateLiceu(@RequestParam(name = "idLiceu") Integer idLiceu,
			@RequestParam(name = "nume") String nume) {
		UnitateDeInvatamant liceu;
		try {
			liceu = liceuDAO.findUnitateDeInvatamantById(idLiceu);
			liceu.setDenumireUnitate(nume);
			liceuDAO.updateUnitateDeInvatamant(liceu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/updateSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public Boolean updateSpecializare(@RequestParam(name = "idSpecializare") Integer idSpecializare,
			@RequestParam(name = "nume") String nume) {
		Specializare specializare;
		try {
			specializare = specializareDAO.findSpecializareById(idSpecializare);
			specializare.setDenumireSpecializare(nume);
			specializareDAO.updateSpecializare(specializare);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value="/elevi2", method = RequestMethod.GET)
	@ResponseBody
	public List<ElevDTO> getElevi2(){
		
		List<DateElev> elevi=dateElevDAO.findAll();
		List<ElevDTO> response=new ArrayList<>();
		
		for (DateElev elev : elevi) {
			ElevDTO elevDTO=new ElevDTO();
			elevDTO.setId(elev.getIdElev());
			elevDTO.setNume(elev.getNumeElev());
			elevDTO.setInitialaTatalui(elev.getInitialaTata());
			elevDTO.setPrenume(elev.getPrenumeElev());
			
			LiceuPOJO l=new LiceuPOJO();
			l.setId(elev.getUnitate().getIdUnitate());
			l.setNume(elev.getUnitate().getDenumireUnitate());
			l.setJudet(elev.getUnitate().getDenumireUnitate());
			
			elevDTO.setLiceu(l);
			
			SpecializarePOJO s=new SpecializarePOJO();
			s.setId(elev.getSpecializare().getIdSpecializare());
			s.setNume(elev.getSpecializare().getDenumireSpecializare());
			
			elevDTO.setSpec(s);
			
			ProfilPOJO pr=new ProfilPOJO();
			pr.setIdProfil(elev.getSpecializare().getIdProfil().getIdProfil());
			pr.setDenumireProfil(elev.getSpecializare().getIdProfil().getDenumireProfil());
			
			elevDTO.setProfil(pr);
			
			ProbaDTO materna=new ProbaDTO();
			ProbaDTO moderna=new ProbaDTO();
			ProbaDTO obligatorie=new ProbaDTO();
			ProbaDTO alegere=new ProbaDTO();
			
			for (Proba proba : elev.getProbe()) {
				if(proba.getIdDisciplinaSpecializare().getLimbaMaterna()==1){
					materna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
					materna.setNota(proba.getNota());
					materna.setNotaContestatie(proba.getNotaContestatie());
					materna.setProbaId(proba.getIdProba());
					if(proba.getNotaContestatie()!=null){
						if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
							materna.setNotaFinala(proba.getNotaContestatie());
						}
						else {
							materna.setNotaFinala(proba.getNota());
						}
					}
					else{
						materna.setNota(proba.getNota());
					}
					materna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
				}
				else if(proba.getIdDisciplinaSpecializare().getLimbaModerna()==1){
					moderna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
					moderna.setNota(proba.getNota());
					moderna.setProbaId(proba.getIdProba());
					if(proba.getNotaContestatie()!=null){
						if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
							moderna.setNotaFinala(proba.getNotaContestatie());
						}
						else {
							moderna.setNotaFinala(proba.getNota());
						}
					}
					else{
						moderna.setNota(proba.getNota());
					}
					moderna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
				}
				else if(proba.getIdDisciplinaSpecializare().getObligatoriu()==1){
					obligatorie.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
					obligatorie.setProbaId(proba.getIdProba());
					obligatorie.setNota(proba.getNota());
					obligatorie.setNotaContestatie(proba.getNotaContestatie());
					if(proba.getNotaContestatie()!=null){
						if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
							obligatorie.setNotaFinala(proba.getNotaContestatie());
						}
						else {
							obligatorie.setNotaFinala(proba.getNota());
						}
					}
					else{
						obligatorie.setNota(proba.getNota());
					}
					
					obligatorie.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
				}else
				{
					alegere.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
					alegere.setNota(proba.getNota());
					alegere.setProbaId(proba.getIdProba());
					alegere.setNotaContestatie(proba.getNotaContestatie());
					if(proba.getNotaContestatie()!=null){
						if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
							alegere.setNotaFinala(proba.getNotaContestatie());
						}
						else {
							alegere.setNotaFinala(proba.getNota());
						}
					}
					else{
						alegere.setNota(proba.getNota());
					}
					
					alegere.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
				}
			}
			elevDTO.setLimbaMaterna(materna);
			elevDTO.setLimbaModerna(moderna);
			elevDTO.setProbaObligatorie(obligatorie);
			elevDTO.setProbaAlegere(alegere);
			response.add(elevDTO);
		}
		

		
		return response;
	}
	
	@RequestMapping(value="/editElev", method = RequestMethod.GET)
	@ResponseBody
	public boolean editElev(@RequestParam(name="elev")String elev){
		System.out.println(elev);
		ObjectMapper om=new ObjectMapper();
		ElevDTO elevDTO;
		DateElev dateElev;
		try {
			elevDTO=om.readValue(elev, ElevDTO.class);
			dateElev=dateElevDAO.findById(elevDTO.getId());
			
			
			
			dateElev.setInitialaTata(elevDTO.getInitialaTatalui());
			dateElev.setNumeElev(elevDTO.getNume());
			dateElev.setPrenumeElev(elevDTO.getPrenume());
			Specializare spec=specializareDAO.findSpecializareById(elevDTO.getSpec().getId());
			dateElev.setSpecializare(spec);
			
			UnitateDeInvatamant udi=liceuDAO.findUnitateDeInvatamantById(elevDTO.getLiceu().getId());
			dateElev.setUnitate(udi);
			dateElevDAO.updateDateElev(dateElev);
			
			List<Proba> probeCurente=dateElev.getProbe();
			
			//edit proba alegere
			for (Proba proba : probeCurente) {
				if(proba.getIdDisciplinaSpecializare().getObligatoriu()==1){
					//edit proba obligatorie
					Proba probaObligatorie=probeDAO.findProbaById(proba.getIdProba());
					probaObligatorie.setNota(elevDTO.getProbaObligatorie().getNota());
					probaObligatorie.setNotaContestatie(elevDTO.getProbaObligatorie().getNotaContestatie());
					
					DisciplinaSpecializare discSpecObligatorie=discSpecDAO.findDisciplinaSpecializareById(elevDTO.getProbaObligatorie().getId());
					probaObligatorie.setIdDisciplinaSpecializare(discSpecObligatorie);
					
					probeDAO.updateProba(probaObligatorie);
				}
				else if(proba.getIdDisciplinaSpecializare().getLimbaMaterna()==1){
					//edit limba materna
					Proba limbaMaterna=probeDAO.findProbaById(proba.getIdProba());
					limbaMaterna.setNota(elevDTO.getLimbaMaterna().getNota());
					limbaMaterna.setNotaContestatie(elevDTO.getLimbaMaterna().getNotaContestatie());
					
					DisciplinaSpecializare discSpecMaterna=discSpecDAO.findDisciplinaSpecializareById(elevDTO.getLimbaMaterna().getId());
					limbaMaterna.setIdDisciplinaSpecializare(discSpecMaterna);
					
					probeDAO.updateProba(limbaMaterna);
				}
				else if(proba.getIdDisciplinaSpecializare().getLimbaModerna()==1){
					//edit limba moderna
					Proba limbaModerna=probeDAO.findProbaById(proba.getIdProba());
					limbaModerna.setNota(elevDTO.getLimbaModerna().getNota());
					limbaModerna.setNotaContestatie(elevDTO.getLimbaModerna().getNotaContestatie());
					
					DisciplinaSpecializare discSpecModerna=discSpecDAO.findDisciplinaSpecializareById(elevDTO.getLimbaModerna().getId());
					limbaModerna.setIdDisciplinaSpecializare(discSpecModerna);
					
					probeDAO.updateProba(limbaModerna);
				}
				else{
					Proba probaAlegere=probeDAO.findProbaById(proba.getIdProba());
					probaAlegere.setNota(elevDTO.getProbaAlegere().getNota());
					probaAlegere.setNotaContestatie(elevDTO.getProbaAlegere().getNotaContestatie());
					
					DisciplinaSpecializare discSpecAlegere=discSpecDAO.findDisciplinaSpecializareById(elevDTO.getProbaAlegere().getId());
					probaAlegere.setIdDisciplinaSpecializare(discSpecAlegere);
					
					probeDAO.updateProba(probaAlegere);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	@RequestMapping(value="/canDeleteSpecializare", method = RequestMethod.GET)
	@ResponseBody
	public boolean canDeleteSpecializare(@RequestParam(name="id")Integer id){
		boolean ok1=true;
		boolean ok2=true;
		Specializare spec;
		
		try {
			spec=specializareDAO.findSpecializareById(id);
			if(spec.getDisciplineSpecializari().size()!=0){
			
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			spec=specializareDAO.findSpecializareById(id);
			if(spec.getElevi().size()!=0){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*@RequestMapping(value="/getElevById", method = RequestMethod.GET)
	@ResponseBody
	public ElevDTO getElevById(@RequestParam(name="id")Integer id){
		
		ElevDTO elevDTO=new ElevDTO();
		DateElev elev=null;
		try {
			 elev=dateElevDAO.findById(id);
			 elevDTO.setId(elev.getIdElev());
				elevDTO.setNume(elev.getNumeElev());
				elevDTO.setInitialaTatalui(elev.getInitialaTata());
				elevDTO.setPrenume(elev.getPrenumeElev());
				
				LiceuPOJO l=new LiceuPOJO();
				l.setId(elev.getUnitate().getIdUnitate());
				l.setNume(elev.getUnitate().getDenumireUnitate());
				l.setJudet(elev.getUnitate().getDenumireUnitate());
				
				elevDTO.setLiceu(l);
				
				SpecializarePOJO s=new SpecializarePOJO();
				s.setId(elev.getSpecializare().getIdSpecializare());
				s.setNume(elev.getSpecializare().getDenumireSpecializare());
				
				elevDTO.setSpec(s);
				
				ProfilPOJO pr=new ProfilPOJO();
				pr.setIdProfil(elev.getSpecializare().getIdProfil().getIdProfil());
				pr.setDenumireProfil(elev.getSpecializare().getIdProfil().getDenumireProfil());
				
				elevDTO.setProfil(pr);
				
				ProbaDTO materna=new ProbaDTO();
				ProbaDTO moderna=new ProbaDTO();
				ProbaDTO obligatorie=new ProbaDTO();
				ProbaDTO alegere=new ProbaDTO();
				
				for (Proba proba : elev.getProbe()) {
					if(proba.getIdDisciplinaSpecializare().getLimbaMaterna()==1){
						materna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						materna.setNota(proba.getNota());
						materna.setNotaContestatie(proba.getNotaContestatie());
						materna.setProbaId(proba.getIdProba());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								materna.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								materna.setNotaFinala(proba.getNota());
							}
						}
						else{
							materna.setNotaFinala(proba.getNota());
						}
						materna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
					else if(proba.getIdDisciplinaSpecializare().getLimbaModerna()==1){
						moderna.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						moderna.setNota(proba.getNota());
						moderna.setProbaId(proba.getIdProba());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								moderna.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								moderna.setNotaFinala(proba.getNota());
							}
						}
						else{
							moderna.setNotaFinala(proba.getNota());
						}
						moderna.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
					else if(proba.getIdDisciplinaSpecializare().getObligatoriu()==1){
						obligatorie.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						obligatorie.setProbaId(proba.getIdProba());
						obligatorie.setNota(proba.getNota());
						obligatorie.setNotaContestatie(proba.getNotaContestatie());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								obligatorie.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								obligatorie.setNotaFinala(proba.getNota());
							}
						}
						else{
							obligatorie.setNotaFinala(proba.getNota());
						}
						
						obligatorie.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}else
					{
						alegere.setId(proba.getIdDisciplinaSpecializare().getIdDisciplinaSpecializare());
						alegere.setNota(proba.getNota());
						alegere.setProbaId(proba.getIdProba());
						alegere.setNotaContestatie(proba.getNotaContestatie());
						if(proba.getNotaContestatie()!=null){
							if(Math.abs(proba.getNotaContestatie()-proba.getNota())>=0.5){
								alegere.setNotaFinala(proba.getNotaContestatie());
							}
							else {
								alegere.setNotaFinala(proba.getNota());
							}
						}
						else{
							alegere.setNotaFinala(proba.getNota());
						}
						
						alegere.setNume(proba.getIdDisciplinaSpecializare().getIdDisciplina().getDenumireDisciplina());
					}
				}
				elevDTO.setLimbaMaterna(materna);
				elevDTO.setLimbaModerna(moderna);
				elevDTO.setProbaObligatorie(obligatorie);
				elevDTO.setProbaAlegere(alegere);
 
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("work");
		return elevDTO;
	}
*/	
	
	
	
	
	@RequestMapping(value = "/numeEleviBySearch", method = RequestMethod.GET)
	@ResponseBody
	public EleviAndroidPOJO getNumeEleviBySearch(@RequestParam(name="keyWord")String keyWord) {
		EleviAndroidPOJO elevi = new EleviAndroidPOJO();
		ElevAndroidPOJO elev;
		System.out.println(keyWord);
		
		List<ElevAndroidPOJO> eleviList = new ArrayList<ElevAndroidPOJO>();
		List<DateElev> listaElevi = new ArrayList<DateElev>();

		System.out.println("----Afisarea elevilor din baza de date----");
		try {
			listaElevi = dateElevDAO.getEleviBySearchWord(keyWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (DateElev dateElev : listaElevi) {

			elev = new ElevAndroidPOJO();
			elev.setId(dateElev.getIdElev());
			elev.setNumeElev(dateElev.getNumeElev());
			elev.setPrenumeElev(dateElev.getPrenumeElev());
			elev.setInitialaTata(dateElev.getInitialaTata());
			
			eleviList.add(elev);
		}

		elevi.setElevi(eleviList);

		return elevi;

	}
	
	@RequestMapping(value = "/editDisciplina", method = RequestMethod.GET)
	@ResponseBody
	public boolean editDisciplina(@RequestParam(name="id")int id,@RequestParam(name="numeNou")String numeNou){
		Disciplina disc;
		try {
			disc=disciplinaDAO.findDisciplinaById(id);
			disc.setDenumireDisciplina(numeNou);
			disciplinaDAO.updateDisciplina(disc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
