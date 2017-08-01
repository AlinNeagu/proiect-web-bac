package proiect.bac.DAO;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;


import proiect.bac.entities.UserDetails;
@Component
public class UserDAO {

	public UserDetails getUserByPasswordAndName(String name,String pass){
		UserDetails userDetails;
		
		EntityManager em=EMgrUtil.createEntityManager();
		userDetails=em.createQuery("select e from UserDetails e",UserDetails.class).getSingleResult();
		System.out.println("done");
		return userDetails;
	}
}
