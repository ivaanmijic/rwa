package com.example.video_voting.repository;

import java.util.List;
import java.util.Optional;

import com.example.video_voting.model.Video;
import com.example.video_voting.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * VideoRepository
 */
public class VideoRepository {

  public Long count() {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      TypedQuery<Long> query = em.createQuery(
          "SELECT COUNT(v) FROM Video v", Long.class);
      return query.getSingleResult();
    } finally {
      em.close();
    }
  }

  public Optional<Video> selectById(Long id) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      Video video = em.find(Video.class, id);
      return Optional.ofNullable(video);
    } finally {
      em.close();
    }
  }

  public List<Video> selectRandom() {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      TypedQuery<Video> query = em.createQuery(
          "SELECT v FROM Video v ORDER BY function('RAND')",
          Video.class);
      List<Video> randomVideos = query.setMaxResults(2).getResultList();
      return randomVideos;
    } finally {
      em.close();
    }
  }

  public List<Video> selectByRankFromOffsetWithLimit(Integer pageNumber, Integer pageSize) {
    EntityManager em = JPAUtil.getEntityManager();

    Integer firstResult = (pageNumber - 1) * pageSize;

    try {
      String queryString = "SELECT v " +
          "FROM Video v " +
          "ORDER BY " +
          "  CASE WHEN v.totalVotes = 0 THEN 1 ELSE 0 END, " +
          "  ( " +
          "    ((v.votes + 1.9208) / v.totalVotes) " +
          "    - 1.96 * SQRT( (v.votes * (v.totalVotes - v.votes)) / v.totalVotes + 0.9604 ) / v.totalVotes" +
          "  ) / (1 + 3.8416 / v.totalVotes) DESC";

      TypedQuery<Video> q = em.createQuery(queryString, Video.class);
      q.setFirstResult(firstResult);
      q.setMaxResults(pageSize);

      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public void insert(Video video) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      if (video.getId() == null) {
        em.persist(video);
      } else {
        em.merge(video);
      }
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

  public void updateVotes(Video video) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      em.getTransaction().begin();

      Query query = em.createQuery(
          "UPDATE Video v SET v.votes = :votes, v.totalVotes = :totalVotes WHERE v.id = :id");
      query.setParameter("votes", video.getVotes());
      query.setParameter("totalVotes", video.getTotalVotes());
      query.setParameter("id", video.getId());

      query.executeUpdate();
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
