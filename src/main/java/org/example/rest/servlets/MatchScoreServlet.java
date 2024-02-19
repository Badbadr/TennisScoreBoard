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
import org.example.util.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/match-score")
@NoArgsConstructor
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchService matchService = new OngoingMatchService(
        new FinishedMatchesPersistenceService(new MatchRepository()),
        new OngoingMatchRepository(),
        new PlayerRepository()
    );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = Arrays.stream(req.getQueryString().split("&")).map(param -> param.split("="))
                .collect(Collectors.toMap(param -> param[0], param -> param[1]));

        String id = params.get("uuid");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        boolean isFirstWin = Boolean.parseBoolean(req.getParameter("isFirstWin"));
        try {
            OngoingMatch match = matchService.updateScore(UUID.fromString(id), isFirstWin);
            resp.getWriter()
                .write(Mapper.mapper.writeValueAsString(match));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
