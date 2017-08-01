package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import proiect.bac.entities.DateElev;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;

@Component
public class DateElevDAO {
	
	//Create
		public void createDateElev( DateElev elev){
			
			EntityManager em = EMgrUtil.createEntityManager();
		
			em.getTransaction().begin();
			em.persist(elev);
			em.getTransaction().commit();
			em.clear();
			
		}
		
		public List<DateElev> getEleviBySearchWord(String word){
			EntityManager em=EMgrUtil.createEntityManager();
			List<DateElev> lista=em.createQuery("SELECT e FROM DateElev e where e.numeElev like '"+word+"%'",DateElev.class).getResultList();
			System.out.println("SELECT e FROM DateElev e where e.numeElev ='"+word+"%'");
			return lista;
		}
		
		//Read
		public DateElev readDateElevByName(String name){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			DateElev elev = em.createQuery("select e from DateElev e where e.numeElev = ?1", DateElev.class).setParameter(1, name).getSingleResult();
			
			return elev;
			
		}
		
		
		
		//Update
		public void updateDateElev(DateElev elev){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			em.getTransaction().begin();
			em.merge(elev);
			em.getTransaction().commit();
			em.clear();
		}
		
		//Delete
		public void deleteDateElevById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			DateElev elev = findDateElevById(id);
			
			em.getTransaction().begin();
			em.remove(elev);
			em.getTransaction().commit();
			em.clear();
		}
		
		public DateElev findDateElevById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			DateElev elev = em.createQuery("select e from DateElev e where e.idElev = ?1",DateElev.class).setParameter(1,id).getSingleResult();
		
			return elev;
		}
	
	public void deleteById(Integer id) {

		

		EntityManager em = EMgrUtil.createEntityManager();
		DateElev date = em.find(DateElev.class, id);
		System.out.println("#######################################################");
		System.out.println("#######################################################");
		System.out.println("#######################################################");
		System.out.println("#######################################################");
		System.out.println("#######################################################");
		em.getTransaction().begin();
		
		System.out.println("0000000000000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000000000000");
		em.remove(date);
		System.out.println("111111111111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111111111111");
		em.getTransaction().commit();
		System.out.println("222222222222222222222222222222222222222222222222222222");
		System.out.println("222222222222222222222222222222222222222222222222222222");
		System.out.println("222222222222222222222222222222222222222222222222222222");
		System.out.println("222222222222222222222222222222222222222222222222222222");
		System.out.println("222222222222222222222222222222222222222222222222222222");
		em.clear();
	}

	public List<DateElev> findAll() {

		EntityManager em = EMgrUtil.createEntityManager();
		em.clear();
		List<DateElev> list = em.createQuery("select e from DateElev e", DateElev.class).getResultList();
		return list;

	}
	public List<DateElev> getUnitati() {

		EntityManager em = EMgrUtil.createEntityManager();

		List<DateElev> list = em.createQuery(" select denumireUnitate from UnitateDeInvatamant   ", DateElev.class).getResultList();
		return list;

	}

	public DateElev findById(Integer id) {

		EntityManager em = EMgrUtil.createEntityManager();

		System.out.println(em);
		DateElev date = em.find(DateElev.class, id);

		return date;
	}
}

