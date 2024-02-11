package org.example.edu;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Match;
import org.example.model.Player;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player();
        player1.setName("Ivan Ivanovich");

        Player player2 = new Player();
        player2.setName("Petr Petrovich");

        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
            System.out.println("Match saved");
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            CriteriaQuery<Match> query = cr.select(root).where(cb.equal(root.get("player1").get("name"), "Ivan Ivanovich"));
            System.out.println(session.createQuery(query).getResultList());
        }
    }
}