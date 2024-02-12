package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.jpa.Match;
import org.example.model.jpa.Player;
import org.example.model.redis.OngoingMatch;
import org.example.repository.jpa.PlayerRepository;
import org.example.repository.redis.OngoingMatchRepository;
import org.example.repository.config.HibernateConfig;
import org.hibernate.Session;

@RequiredArgsConstructor
public class OngoingMatchService {

    private final OngoingMatchRepository ongoingMatchRepository;
    private final PlayerRepository playerRepository;

    public OngoingMatch createMatch(String name1, String name2) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
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
            OngoingMatch match = OngoingMatch.builder()
                    .player1(player1)
                    .player2(player2)
                    .build();
            ongoingMatchRepository.save(match);
            session.getTransaction().commit();
            return match;
        }
    }
}
