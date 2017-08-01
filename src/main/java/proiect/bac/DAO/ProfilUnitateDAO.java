package proiect.bac.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import proiect.bac.entities.Profil;
import proiect.bac.entities.ProfilUnitate;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;

@Component
public class ProfilUnitateDAO {

	//Create
		public void createProfilUnitate(ProfilUnitate p){
			
			EntityManager em = EMgrUtil.createEntityManager();
		
			em.getTransaction().begin();

			em.persist(p);
	
			em.getTransaction().commit();
			em.clear();
		}
		
		//Read
		public List<ProfilUnitate> readProfilUnitateByIdUnitate(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			List <ProfilUnitate> p = em.createQuery("select p from ProfilUnitate p where p.idUnitate = ?1", ProfilUnitate.class).setParameter(1, id).getResultList();
			
			return p;
		}
		
		
		
		//Update
		public void updateProfilUnitate(ProfilUnitate p){
			
			EntityManager em = EMgrUtil.createEntityManager();
		
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			em.clear();
		}
		
		//Delete
		public void deleteProfilUnitateById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			ProfilUnitate p = findProfilUnitateById(id);
			
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			em.clear();
		}
		
		private ProfilUnitate getProfilUnitate (Integer idProfil, Integer idUnitateDeInvatamant){
			EntityManager em = EMgrUtil.createEntityManager();
			
			  return (ProfilUnitate) em.createQuery("SELECT pu FROM ProfilUnitate pu WHERE pu.pk.profil.idProfil = :idProfil and pu.pk.unitate.idUnitate = :idUnitate").setParameter("idProfil", idProfil).setParameter("idUnitate", idUnitateDeInvatamant).getSingleResult();
		}
		
		public void removeProfilUnitate(Integer idProfil, Integer idUnitateDeInvatamant){
			System.out.println("idProfilUnitate " +idProfil);
			EntityManager em = EMgrUtil.createEntityManager();
			em.getTransaction().begin();
			ProfilUnitate pu=   (ProfilUnitate) em.createQuery("SELECT pu FROM ProfilUnitate pu WHERE pu.pk.profil.idProfil = :idProfil and pu.pk.unitate.idUnitate = :idUnitate").setParameter("idProfil", idProfil).setParameter("idUnitate", idUnitateDeInvatamant).getSingleResult();
		

	      

	        UnitateDeInvatamant ui = pu.getPk().getUnitate();
	        Profil profil = pu.getPk().getProfil();

	        em.remove(pu);
	        System.out.println("before removing pu from unitate: " + ui.getProfiluri().size());
	        ui.getProfiluri().remove(pu);
	        System.out.println("after removing pu from unitate: " + ui.getProfiluri().size());
	      //  System.out.println("before removing pu from profil: " + profil..size());
	       // profil.getProfilUnitate().remove(pu);
	       // System.out.println("after removing pu from profil: " + profil.getProfiluri().size());

	        em.merge(ui);
	        em.merge(profil);
	        em.flush();
	        em.getTransaction().commit();
			em.clear();
			
		}
		public ProfilUnitate findProfilUnitateById(Integer id){
			
			EntityManager em = EMgrUtil.createEntityManager();
			
			//Specializare specializare = em.find(Specializare.class, id);
			ProfilUnitate p = em.createQuery("select e from ProfilUnitate e where e.idProfilUnitate = ?1",ProfilUnitate.class).setParameter(1,id).getSingleResult();
			System.out.println(p);
			return p;
		}
}
