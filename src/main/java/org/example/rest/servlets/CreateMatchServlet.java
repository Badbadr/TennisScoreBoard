package org.example.rest.servlets;

import jakarta.servlet.ServletContext;
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

@WebServlet(urlPatterns = "/api/new-match")
@NoArgsConstructor
public class CreateMatchServlet extends HttpServlet {
    private OngoingMatchService matchService;
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        matchService = (OngoingMatchService) ctx.getAttribute("ongoingMatchService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");
        OngoingMatch match = matchService.createMatch(player1Name, player2Name);
        resp.getWriter().write(Mapper.mapper.writeValueAsString(match));
    }
}
