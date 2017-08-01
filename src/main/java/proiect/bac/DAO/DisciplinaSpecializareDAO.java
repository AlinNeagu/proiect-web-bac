package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import proiect.bac.entities.Disciplina;
import proiect.bac.entities.DisciplinaSpecializare;
import proiect.bac.entities.Proba;
import proiect.bac.entities.Specializare;

@Component
public class DisciplinaSpecializareDAO {
	
	
	
	public DisciplinaSpecializare getIdByName(String name){
		EntityManager em=EMgrUtil.createEntityManager();
		
		DisciplinaSpecializare discSpec=em.createQuery("select e from DisciplinaSpecializare e where idDisciplinaSpecializare = ?1",DisciplinaSpecializare.class).setParameter(1, name).getSingleResult();
		return discSpec;
	}
	//Create
			public void createDisciplinaSpecializare(DisciplinaSpecializare d){
				
				EntityManager em = EMgrUtil.createEntityManager();
			
				em.getTransaction().begin();
				em.persist(d);
				em.getTransaction().commit();
				em.clear();
			}
			
			//Read
			public DisciplinaSpecializare readDisciplinaSpecializareByIdSpecializare(Integer id){
				
				EntityManager em = EMgrUtil.createEntityManager();
				
				DisciplinaSpecializare p = em.createQuery("select p from DisciplinaSpecializare p where p.idDisciplinaSpecializare = ?1",DisciplinaSpecializare.class).setParameter(1, id.toString()).getSingleResult();
				
				return p;
			}
			
			
			
			//Update
			public void updateDisciplinaSpecializare(DisciplinaSpecializare d){
				
				EntityManager em = EMgrUtil.createEntityManager();
				
				em.getTransaction().begin();
				em.merge(d);
				em.getTransaction().commit();
				em.clear();
			}
			
			//Delete
			public void deleteDisciplinaSpecializareById(Integer id){
				
				EntityManager em = EMgrUtil.createEntityManager();
				
				DisciplinaSpecializare p = findDisciplinaSpecializareById(id);
				
				em.getTransaction().begin();
				em.remove(p);
				em.getTransaction().commit();
				em.clear();
			}
			
			public List<DisciplinaSpecializare> findAll(){
				EntityManager em=EMgrUtil.createEntityManager();
				
				List<DisciplinaSpecializare> probe=em.createQuery("SELECT e FROM DisciplinaSpecializare e",DisciplinaSpecializare.class).getResultList();
				
				return probe;
			}
			
			public DisciplinaSpecializare findDisciplinaSpecializareById(Integer id){
				
				EntityManager em = EMgrUtil.createEntityManager();
				
				DisciplinaSpecializare p = em.createQuery("select e from DisciplinaSpecializare e where e.idDisciplinaSpecializare = ?1",DisciplinaSpecializare.class).setParameter(1,id).getSingleResult();
		
				return p;
			}
			
			public DisciplinaSpecializare getIdByName(Integer integer, Integer idSpec) {
				EntityManager em = EMgrUtil.createEntityManager();
				
				//DisciplinaSpecializare p = em.createQuery("select e from DisciplinaSpecializare e where ID_Disciplina = ?1 and id_Specializare = ?2",DisciplinaSpecializare.class).setParameter(1,integer).getSingleResult();
				DisciplinaSpecializare p = (DisciplinaSpecializare) em.createQuery("select e from DisciplinaSpecializare e where ID_Disciplina = " +integer + " and id_Specializare = "+idSpec+" ",DisciplinaSpecializare.class).getSingleResult();
				return p;
			}
			
			
}
