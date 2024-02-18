package org.example.rest.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.example.repository.jpa.MatchRepository;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.util.Mapper;

@WebServlet(urlPatterns = "/api/matches")
@NoArgsConstructor
public class MatchesServlet extends HttpServlet {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(new MatchRepository());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter()
                .write(Mapper.mapper.writeValueAsString(finishedMatchesPersistenceService.findAllMatches()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
