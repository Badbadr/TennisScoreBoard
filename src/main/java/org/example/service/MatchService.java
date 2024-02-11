package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Match;
import org.example.model.Player;
import org.example.repository.MatchRepository;
import org.example.repository.PlayerRepository;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public List<Match> findAllMatches() {
        return matchRepository.findAll();
    }

    public Match createMatch(String name1, String name2) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Player player1 = playerRepository.findPlayerByName(name1);
            if (player1 == null) {
                player1 = new Player();
                player1.setName(name1);
                session.persist(player1);
            }
            Player player2 = playerRepository.findPlayerByName(name2);
            if (player2 == null) {
                player2 = new Player();
                player2.setName(name2);
                session.persist(player2);
            }
            Match match = new Match();
            match.setPlayer1(player1);
            match.setPlayer2(player2);
            session.persist(match);
            session.getTransaction().commit();
            return match;
        }
    }
}
