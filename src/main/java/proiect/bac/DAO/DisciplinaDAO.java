package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import proiect.bac.entities.Disciplina;
import proiect.bac.entities.Proba;
@Component
public class DisciplinaDAO {

	//Create
	public void createDisciplina( Disciplina d){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		em.clear();
	}
	
	//Read
	public Disciplina readDisciplinaByName(String name){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		Disciplina disciplina = em.createQuery("select e from Disciplina e where e.denumireDisciplina = ?1", Disciplina.class).setParameter(1, name).getSingleResult();
		
		return disciplina;
	}
	
	
	
	//Update
	public void updateDisciplina(Disciplina disciplina){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(disciplina);
		em.getTransaction().commit();
		em.clear();
	} 
	
	//Delete
	public void deleteDisciplinaById(Integer id){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		Disciplina disciplina = findDisciplinaById(id);
		
		em.getTransaction().begin();
		em.remove(disciplina);
		em.getTransaction().commit();
		em.clear();
	}
	
	public List<Disciplina> findAll(){
		
		EntityManager em=EMgrUtil.createEntityManager();
		List<Disciplina> discipline=em.createQuery("SELECT e FROM Disciplina e",Disciplina.class).getResultList();
		
		return discipline;
	}
	
	public Disciplina findDisciplinaById(Integer id){
		
		EntityManager em = EMgrUtil.createEntityManager();
		
		//Specializare specializare = em.find(Specializare.class, id);
		Disciplina d = em.createQuery("select e from Disciplina e where e.idDisciplina = ?1",Disciplina.class).setParameter(1,id).getSingleResult();
		System.out.println(d);
		return d;
	}
	public Disciplina getIdByName(String name){
		EntityManager em=EMgrUtil.createEntityManager();
		
		Disciplina disciplina=em.createQuery("select e from Disciplina e where denumireDisciplina=?1",Disciplina.class).setParameter(1, name).getSingleResult();
		return disciplina;
		
	}
}
