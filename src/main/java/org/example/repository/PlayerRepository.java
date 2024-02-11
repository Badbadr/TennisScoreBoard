package org.example.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.example.model.Player;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

@NoArgsConstructor
public class PlayerRepository {

    public Player findPlayerByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Player> cq = cb.createQuery(Player.class);
            Root<Player> root = cq.from(Player.class);
            TypedQuery<Player> query = session.createQuery(cq.select(root).where(cb.equal(root.get("name"), name)));
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }
}
