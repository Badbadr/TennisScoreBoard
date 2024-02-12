package org.example.repository.jpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.example.model.jpa.Match;
import org.example.repository.config.HibernateConfig;
import org.hibernate.Session;

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

    public List<Match> findAll() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Match> query = cr.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    public Match save(Match match) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.persist(match);
            return match;
        }
    }
}
