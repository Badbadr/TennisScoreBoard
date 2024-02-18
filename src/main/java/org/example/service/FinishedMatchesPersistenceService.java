package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.jpa.Match;
import org.example.model.redis.OngoingMatch;
import org.example.repository.jpa.MatchRepository;

import java.util.List;

@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository;

    public List<Match> findAllMatches() {
        return matchRepository.findAll();
    }
    public Match save(OngoingMatch finishedMatch) {
        Match match = new Match();
        match.setPlayer1(finishedMatch.getPlayer1());
        match.setPlayer2(finishedMatch.getPlayer2());
        match.setWinner(finishedMatch.getWinner());
        return matchRepository.save(match);
    }

}
