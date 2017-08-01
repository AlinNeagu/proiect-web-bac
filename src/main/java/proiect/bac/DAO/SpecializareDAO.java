package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import proiect.bac.entities.Specializare;
@Component
public class SpecializareDAO {

	
	
	public Specializare getIdByName(String name){
		EntityManager em=EMgrUtil.createEntityManager();
		
		Specializare spec=em.createQuery("select e from Specializare e where denumireSpecializare=?1",Specializare.class).setParameter(1, name).getSingleResult();
		return spec;
	}
	
	//Create
	public void createSpecializare( Specializare s){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.clear();
	
	}
	
	//Read
	public Specializare readSpecializareByName(String name){
		Specializare spec = null;
		EntityManager em = EMgrUtil.createEntityManager();
		spec = em.createQuery("select e from Specializare e where e.denumireSpecializare = ?1", Specializare.class).setParameter(1, name).getSingleResult();
		
		return spec;
	}
	
	public static void main(String[] args) {
		SpecializareDAO sp=new 
				SpecializareDAO();
		System.out.println(sp.readSpecializareByName("Matematica-Informatica").getElevi());
	}
	
	//Update
	public void updateSpecializare(Specializare specializare){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(specializare);
		em.getTransaction().commit();

	}
	

	//Delete
	public void deleteSpecializareById(Integer id){
		
		EntityManager em = EMgrUtil.createEntityManager();
		em.clear();
		Specializare specializare = em.find(Specializare.class, id);
		
		em.getTransaction().begin();
		em.refresh(specializare);
		em.remove(specializare);
		em.getTransaction().commit();
		em.clear();
	}
	
	public Specializare findSpecializareById(Integer id){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		//Specializare specializare = em.find(Specializare.class, id);
		Specializare sp = em.createQuery("select e from Specializare e where e.idSpecializare = ?1",Specializare.class).setParameter(1,id).getSingleResult();
		System.out.println(sp);
		return sp;
	}
	
	//
	

	public List<Specializare> findAll() {

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("BAC");
		EntityManager em=emf.createEntityManager();

		List<Specializare> list = em.createQuery("select e from Specializare e", Specializare.class).getResultList();
		em.close();
		emf.close();
		
		return list;

	}
	
	public Specializare getSpecializareByName(String name){
		EntityManager em=EMgrUtil.createEntityManager();
		
		Specializare spec=em.createQuery("SELECT e FROM Specializare e WHERE e.denumireSpecializare=?1",Specializare.class).setParameter(1, name).getSingleResult();
		
		return spec;
	}
	

}
