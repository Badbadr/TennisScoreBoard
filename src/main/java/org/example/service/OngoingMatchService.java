package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.jpa.Player;
import org.example.model.redis.OngoingMatch;
import org.example.model.redis.PlayerScore;
import org.example.repository.config.HibernateConfig;
import org.example.repository.jpa.PlayerRepository;
import org.example.repository.redis.OngoingMatchRepository;
import org.hibernate.Session;

import java.util.UUID;

@RequiredArgsConstructor
public class OngoingMatchService {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
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
                    .id(UUID.randomUUID())
                    .player1(player1)
                    .player2(player2)
                    .playerScore1(new PlayerScore())
                    .playerScore2(new PlayerScore())
                    .build();
            ongoingMatchRepository.save(match);
            session.getTransaction().commit();
            return match;
        }
    }

    public OngoingMatch updateScore(UUID id, boolean isFirstWin) throws Exception {
        OngoingMatch match = getMatchById(id);
        if (match == null) {
            throw new Exception("Match is finished");
        }
        match.updateScore(isFirstWin);
        if (match.getWinner() != null) {
            finishedMatchesPersistenceService.save(match);
            delete(match.getId());
            return match;
        } else {
            return ongoingMatchRepository.save(match);
        }
    }

    public OngoingMatch getMatchById(UUID id) {
        return ongoingMatchRepository.get(id);
    }

    public void delete(UUID id) {
        ongoingMatchRepository.delete(id);
    }
}
