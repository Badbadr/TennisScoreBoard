package org.example.rest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.example.model.redis.OngoingMatch;
import org.example.repository.jpa.MatchRepository;
import org.example.repository.jpa.PlayerRepository;
import org.example.repository.redis.OngoingMatchRepository;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.OngoingMatchService;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/new-match")
@NoArgsConstructor
public class CreateMatchServlet extends HttpServlet {
    private final OngoingMatchService matchService = new OngoingMatchService(
        new FinishedMatchesPersistenceService(new MatchRepository()),
        new OngoingMatchRepository(),
        new PlayerRepository()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-------------------");
        System.out.println(req.getQueryString());
        System.out.println("-------------------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");
        OngoingMatch match = matchService.createMatch(player1Name, player2Name);
        resp.setStatus(HttpServletResponse.SC_PERMANENT_REDIRECT);
        resp.sendRedirect("http://localhost:8080/app/match-score?uuid=" + match.getId());
    }
}
