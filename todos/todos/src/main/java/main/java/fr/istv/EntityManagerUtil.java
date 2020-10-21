package main.java.fr.istv;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtil {
  private static final EntityManagerFactory entityManagerFactory;
  static {
    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("StudentService");
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();

  }
}