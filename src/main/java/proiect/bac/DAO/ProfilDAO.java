package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import proiect.bac.entities.Disciplina;
import proiect.bac.entities.Profil;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;

@Component
public class ProfilDAO {
	
	//Create
		public void createProfil(Profil profil){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(profil);
			em.getTransaction().commit();
			em.clear();
		}
		
		//Read
		public Profil readProfilByName(String name){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			Profil profil = em.createQuery("select e from Profil e where e.denumireProfil = ?1", Profil.class).setParameter(1, name).getSingleResult();
			
			return profil;
		}
		
		
		
		//Update
		public void updateProfil(Profil profil){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			Profil p=em.find(Profil.class, profil.getIdProfil());
			p.setDenumireProfil(profil.getDenumireProfil());
			em.getTransaction().commit();
			em.clear();
		}
		
		//Delete
		public void deleteProfilById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			Profil profil = findProfilById(id);
			
			em.getTransaction().begin();
			em.remove(profil);
			em.getTransaction().commit();
			em.clear();
		}
		
		public Profil findProfilById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			//Specializare specializare = em.find(Specializare.class, id);
			Profil profil = em.createQuery("select e from Profil e where e.idProfil = ?1",Profil.class).setParameter(1,id).getSingleResult();
			System.out.println(profil);
			return profil;
		}
		
//
		

		public List<Profil> findAll() {

			EntityManager em = EMgrUtil.createEntityManager();
		
			List<Profil> list = em.createQuery("select e from Profil e", Profil.class).getResultList();
		
			return list;

		}

		
		public Profil getProfilByName(String name){
			EntityManager em=EMgrUtil.createEntityManager();
			Profil profil=em.createQuery("SELECT e FROM Profil e where e.denumireProfil=?1",Profil.class).setParameter(1, name).getSingleResult();
			
			return profil;
		}
		
		
}