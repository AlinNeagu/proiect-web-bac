package example.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proiect.bac.entities.DateElev;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;



public class Main {
	 public static void main( String[ ] args ) {
			
		   
		   
	      EntityManagerFactory emf = Persistence.createEntityManagerFactory( "test" );
	      
	      EntityManager em = emf.createEntityManager( );
	      em.getTransaction( ).begin( );
         
	      DateElev elev = new DateElev();
	      Specializare spec = new Specializare();
	      spec.setIdSpecializare(1);
	      UnitateDeInvatamant unitate = new UnitateDeInvatamant();
	      unitate.setIdUnitate(2);
	      
	      elev.setNumeElev( "Tudoras" );
	      elev.setPrenumeElev( "Bogdan" );
	      elev.setInitialaTata( "GH" );
	      elev.setSpecializare(spec);
	      elev.setUnitate(unitate);
	      //search by name
	    	  List<DateElev> a=em.createQuery("SELECT e FROM DateElev e where e.numeElev like ?1 ").setParameter(1, "Moise").getResultList();
	      
	      em.persist( elev );
	      em.getTransaction( ).commit( );
	      
	      for (DateElev da : a) {
			System.out.println(da);
		}
	      
	      em.close( );
	      emf.close( );
	   }

}
