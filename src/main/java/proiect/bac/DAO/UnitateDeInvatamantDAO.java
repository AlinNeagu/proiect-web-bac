package proiect.bac.DAO;

import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import proiect.bac.entities.DateElev;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;
import proiect.bac.entities.UnitateDeInvatamant;

@Component

public class UnitateDeInvatamantDAO {

	//Create
	
		public void Persist(UnitateDeInvatamant licue){
			EntityManager em = EMgrUtil.createEntityManager();
			em.getTransaction().begin();
			em.persist(licue);
			em.getTransaction().commit();
			
			em.clear();
			
		}
		
		
		public UnitateDeInvatamant getIdByName(String name){
			EntityManager em=EMgrUtil.createEntityManager();
			
			UnitateDeInvatamant unitate =em.createQuery("select e from UnitateDeInvatamant e where denumireUnitate=?1",UnitateDeInvatamant.class).setParameter(1, name).getSingleResult();
			return unitate;
		}
		public void createUnitateDeInvatamant(Integer idUnitate, UnitateDeInvatamant centru, String denumire, String judet){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			UnitateDeInvatamant unitate = new UnitateDeInvatamant();
			unitate.setIdUnitate(idUnitate);
			unitate.setCentru(centru);
			unitate.setDenumireUnitate(denumire);
			unitate.setJudet(judet);
			
			em.getTransaction().begin();
			em.merge(unitate);
			em.getTransaction().commit();
			em.clear();
		}
		
		//create liceu for UI
		public void insertLiceu(UnitateDeInvatamant unitate ){
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(unitate);
			em.getTransaction().commit();
			
		}
		
		//take the profile from "Profil"
		public void profilUnitate(UnitateDeInvatamant unitatee){
			
		
		EntityManager em = EMgrUtil.createEntityManager();
		UnitateDeInvatamant unitate = em.find(UnitateDeInvatamant.class, 1);
	
		em.getTransaction().commit();

		}
		
		
		
		
		
		
		//Read
		public List<UnitateDeInvatamant> readUnitateDeInvatamant(){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			List<UnitateDeInvatamant> listaUnitati = em.createQuery("select denumireUnitate, judet from UnitateDeInvatamant", UnitateDeInvatamant.class).getResultList();
			em.getTransaction().commit();
			em.flush();
			return listaUnitati;
		}
		
		//Update
		public void updateUnitateDeInvatamant(UnitateDeInvatamant unitate){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.merge(unitate);
			em.getTransaction().commit();
	
		}
		
		//Delete
		public void deleteUnitateDeInvatamantById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			UnitateDeInvatamant unitate = findUnitateDeInvatamantById(id);
			
			em.getTransaction().begin();
			em.remove(unitate);
			em.getTransaction().commit();

		}
		
		public UnitateDeInvatamant findUnitateDeInvatamantById(Integer id){
			EntityManager em = EMgrUtil.createEntityManager();
			//System.out.println(em);
			UnitateDeInvatamant unitate = em.find(UnitateDeInvatamant.class, id);
			Hibernate.initialize(unitate.getProfiluri());
			return unitate;
		}
		//
		

		public List<UnitateDeInvatamant> findAll() {

			EntityManager em = EMgrUtil.createEntityManager();
			System.out.println("********** Unitate de invatamant******");
			List<UnitateDeInvatamant> list = em.createQuery("select e from UnitateDeInvatamant e", UnitateDeInvatamant.class).getResultList();
			System.out.println("**************************");
			return list;

		}
		public List<UnitateDeInvatamant> getUnitati() {

			EntityManager em = EMgrUtil.createEntityManager();

			List<UnitateDeInvatamant> list = em.createQuery(" select denumireUnitate from UnitateDeInvatamant   ", UnitateDeInvatamant.class).getResultList();
			return list;

		}

		public UnitateDeInvatamant getUnitateByName(String name){
			EntityManager em=EMgrUtil.createEntityManager();
			UnitateDeInvatamant unitate=em.createQuery("SELECT e FROM UnitateDeInvatamant e WHERE e.denumireUnitate=?1",UnitateDeInvatamant.class).setParameter(1, name).getSingleResult();
			return unitate;
		}
		
	}

