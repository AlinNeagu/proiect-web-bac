package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import proiect.bac.entities.DateElev;
import proiect.bac.entities.DisciplinaSpecializare;
import proiect.bac.entities.Proba;
import proiect.bac.entities.Specializare;

@Component
public class ProbaDAO {

	public DisciplinaSpecializare discSpec;

		//Create
		public void createProba( Proba proba){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.merge(proba);
			em.getTransaction().commit();
			em.clear();
		}
		
		public List<Proba> findAll(){
			EntityManager em =EMgrUtil.createEntityManager();
			List<Proba> probe=em.createQuery("SELECT e FROM Proba e",Proba.class).getResultList();
			
			return probe;
			
			
		}
		
	//Read
		public Proba readProbaByIdElev(DateElev idElev){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			Proba proba = em.createQuery("select e from Proba e where e.idElev = ?1", Proba.class).setParameter(1, idElev).getSingleResult();
			
			return proba;
		}		
		
		//Update
		public void updateProba(Proba proba){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.merge(proba);
			em.getTransaction().commit();
			em.clear();
		}
		
		//Delete
		public void deleteProbaById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			Proba proba = findProbaById(id);
			
			em.getTransaction().begin();
			em.remove(proba);
			em.getTransaction().commit();
			em.clear();
		}
		
		public Proba findProbaById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			Proba proba = em.createQuery("select e from Proba e where e.idProba = ?1",Proba.class).setParameter(1,id).getSingleResult();
			System.out.println(proba);
			return proba;
		}
		
		public Integer getIdByName(String name){
			EntityManager em=EMgrUtil.createEntityManager();
			
			Proba proba=em.createQuery("select e from Proba e where denumireProba=?1",Proba.class).setParameter(1, name).getSingleResult();
			return null;
			
		}
		
}