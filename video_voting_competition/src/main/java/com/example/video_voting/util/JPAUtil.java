package com.example.video_voting.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * JPAUtil
 */
public class JPAUtil {

  private static final String PERSISTENCE_UNIT_NAME = "videoPU";
  private static EntityManagerFactory emf;

  public static EntityManagerFactory getEMF() {
    if (emf == null) {
      emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return emf;
  }

  public static EntityManager getEntityManager() {
    return getEMF().createEntityManager();
  }

  public static void shutdown() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }
}
