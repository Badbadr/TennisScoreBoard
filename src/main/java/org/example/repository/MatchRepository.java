package org.example.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.example.model.Match;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor
public class MatchRepository {

    public Match findMatchById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                return session.get(Match.class, id);
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public List<Match> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Match> query = cr.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    public List<Match> create() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class).getResultList();
        }
    }
}
