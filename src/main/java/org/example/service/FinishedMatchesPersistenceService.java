package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Pageable;
import org.example.model.jpa.Match;
import org.example.model.jpa.Player;
import org.example.model.redis.OngoingMatch;
import org.example.repository.jpa.MatchRepository;
import org.example.repository.jpa.PlayerRepository;

import java.util.List;

@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public Pageable<Match> findAllMatches(int page, int pageSize) {
        Pageable<Match> pageable = new Pageable<>();
        List<Match> matches = matchRepository.findAll(page, pageSize);
        pageable.setElements(matches);
        pageable.setTotalElements(matchRepository.count());
        pageable.setPage(page);
        pageable.setTotalPages(pageable.totalElements / pageSize + 1);
        pageable.setPageSize(pageSize);
        return pageable;
    }

    public Pageable<Match> findMatchesByPlayerName(String name, int pageSize, int page) {
        Pageable<Match> pageable = new Pageable<>();
        List<Match> matches = matchRepository.findMatchesByPlayer1OrPlayer2(name, pageSize, page);
        pageable.setElements(matches);
        pageable.setTotalElements(matches.size());
        pageable.setPage(page);
        pageable.setTotalPages(matches.size() / pageSize + 1);
        pageable.setPageSize(pageSize);
        return pageable;
    }

    public int getMatchesCount() {
        return matchRepository.count();
    }

    public Match save(OngoingMatch finishedMatch) {
        Match match = new Match();
        Player player1 = playerRepository.findPlayerByName(finishedMatch.getPlayer1().getName());
        Player player2 = playerRepository.findPlayerByName(finishedMatch.getPlayer2().getName());
        Player winner = playerRepository.findPlayerByName(finishedMatch.getWinner().getName());
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setWinner(winner);
        return matchRepository.save(match);
    }

}
