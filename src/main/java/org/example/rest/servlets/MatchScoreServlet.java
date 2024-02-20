package org.example.rest.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.example.model.redis.OngoingMatch;
import org.example.service.OngoingMatchService;
import org.example.util.Mapper;
import org.example.util.QueryParamsCollector;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/match-score")
@NoArgsConstructor
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchService matchService;
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        matchService = (OngoingMatchService) ctx.getAttribute("ongoingMatchService");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = QueryParamsCollector.collect(req.getQueryString());

        String id = params.get("uuid");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            resp.getWriter()
                .write(Mapper.mapper.writeValueAsString(matchService.getMatchById(UUID.fromString(id))));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
