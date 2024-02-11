package org.example;

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
    }
}