package proiect.bac.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Services {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test");
		EntityManager entitymanager = emfactory.createEntityManager();

		// jpql query example for the export
		Query q = entitymanager.createNativeQuery(
				"select numeElev, prenumeElev, initialaTata, denumireUnitate, denumireSpecializare, "
						+ "notaRom, contestRom, LimbaMod, notaLimbaMod, Obligatorie, "
						+ "notaObl, contestObl, Alegere, notaAlegere, contestAlegere " + "from Export ");
	
		List<Object[]> elevi = q.getResultList();

		for (Object[] a : elevi) {
			System.out.println(" " + a[0] + " " + a[1] + " " + a[2] + " " + a[3] + " " + a[4] + " " + a[5] + " " + a[6]
					+ " " + a[7] + " " + a[8] + " " + a[9] + " " + a[10] + " " + a[11] + " " + a[12] + " " + a[13] + " "
					+ a[14]);

		}

		/*
		 * //Scalar function Query query = entitymanager. createQuery(
		 * "Select numeElev from DateElev");
		 * 
		 * 
		 * 
		 * List<String> list = query.getResultList();
		 * 
		 * for(String e:list) { System.out.println(e); }
		 */
	}
}