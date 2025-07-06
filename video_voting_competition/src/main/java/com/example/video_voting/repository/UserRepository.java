package com.example.video_voting.repository;

import java.util.List;
import java.util.Optional;

import com.example.video_voting.model.User;
import com.example.video_voting.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 * UserRepository
 */
public class UserRepository {

  public User save(User user) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      if (user.getId() == null) {
        em.persist(user);
      } else {
        user = em.merge(user);
      }
      em.getTransaction().commit();
      return user;

    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      throw e;

    } finally {
      em.close();
    }

  }

  public Optional<User> fetchById(Long id) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
      User user = em.find(User.class, id);
      return Optional.ofNullable(user);
    } finally {
      em.close();
    }
  }

  public Optional<User> fetchByUsername(String username) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
      TypedQuery<User> query = em.createQuery(
          "SELECT u FROM User WHERE u.username = :username",
          User.class);
      query.setParameter("username", username);
      return Optional.ofNullable(query.getSingleResult());

    } catch (NoResultException e) {
      return Optional.empty();
    } finally {
      em.close();
    }
  }

  public List<User> fetchAll() {
    EntityManager em = JPAUtil.getEntityManager();

    try {
      TypedQuery<User> q = em.createQuery(
          "SELECT u FROM User u",
          User.class);
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public void delete(Long id) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
      em.getTransaction().begin();

      em.createQuery("DELETE FROM User u WHERE u.id = :id")
          .setParameter("id", id)
          .executeUpdate();

      em.getTransaction().commit();

    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      throw e;

    } finally {
      em.close();
    }
  }

}
