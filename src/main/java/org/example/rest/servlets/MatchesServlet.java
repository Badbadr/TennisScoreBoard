package org.example.rest.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.example.repository.jpa.MatchRepository;
import org.example.repository.jpa.PlayerRepository;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.util.Mapper;
import org.example.util.QueryParamsCollector;

import java.util.Map;

@WebServlet(urlPatterns = "/api/matches")
@NoArgsConstructor
public class MatchesServlet extends HttpServlet {
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) ctx.getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = QueryParamsCollector.collect(req.getQueryString());

        int page;
        int pageSize;
        try {
            page = Integer.parseInt(params.get("page"));
            pageSize = Integer.parseInt(params.get("pageSize"));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            resp.getWriter()
                .write(Mapper.mapper.writeValueAsString(finishedMatchesPersistenceService.findAllMatches(page, pageSize)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
