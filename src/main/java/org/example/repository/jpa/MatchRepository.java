package org.example.repository.jpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.example.model.jpa.Match;
import org.example.repository.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import java.util.List;

@NoArgsConstructor
public class MatchRepository {

    public Match findMatchById(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            try {
                return session.get(Match.class, id);
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public List<Match> findAll(int page, int pageSize) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Match> query = cr.select(root);
            SelectionQuery<Match> q = session.createQuery(query);
            q.setFirstResult((page - 1) * pageSize);
            q.setMaxResults(pageSize);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int count() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Long> query = cr.select(cb.count(root));
            return session.createQuery(query).getSingleResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Match save(Match match) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
            return match;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Match> findMatchesByPlayer1OrPlayer2(String name, int page, int pageSize) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Match> query = cr.select(root).where(
                    cb.or(
                            cb.like(root.get("player1").get("name"), name),
                            cb.like(root.get("player2").get("name"), name),
                            cb.like(root.get("winner").get("name"), name)
                    )
            ).orderBy(cb.desc(root.get("id")));
            SelectionQuery<Match> q = session.createQuery(query);
            q.setFirstResult((page - 1) * pageSize);
            q.setMaxResults(pageSize);
            return q.list();
        }
    }
}
