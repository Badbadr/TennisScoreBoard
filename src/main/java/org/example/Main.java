package org.example;

import org.example.repository.jpa.PlayerRepository;
import org.example.repository.redis.OngoingMatchRepository;
import org.example.service.OngoingMatchService;

public class Main {
    public static void main(String[] args) {
        OngoingMatchService matchService = new OngoingMatchService(new OngoingMatchRepository(), new PlayerRepository());
        System.out.println(matchService.createMatch("Test1 player1", "Test1 player2"));
    }
}