package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.jpa.Match;
import org.example.repository.jpa.MatchRepository;

import java.util.List;

@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public List<Match> findAllMatches() {
        return matchRepository.findAll();
    }


}
