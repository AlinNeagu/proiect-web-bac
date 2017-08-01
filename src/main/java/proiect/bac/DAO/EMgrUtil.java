package proiect.bac.DAO;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;


public class EMgrUtil {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager[]=new EntityManager[5];
	
	public static EntityManager createEntityManager() {
		EntityManager freeEm = null;
		
			try {
				if(entityManagerFactory==null)
					entityManagerFactory = Persistence.createEntityManagerFactory("BAC");
				for(int i=0;i<5;i++){
					if(entityManager[i]!=null){
						if(entityManager[i].getTransaction().isActive()==false)
							return entityManager[i];
					}
				}
				for(int i=0;i<5;i++){
					if(entityManager[i]==null){
						entityManager[i]=entityManagerFactory.createEntityManager();
						freeEm=entityManager[i];
						break;
					}
				}
			} catch (ExceptionInInitializerError e) {
				throw e;
			}
		
		return freeEm;
	}

	public static void close(EntityManager em) {

		em.close();
		entityManagerFactory.close();
	}
}