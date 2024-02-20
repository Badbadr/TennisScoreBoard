package org.example.rest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;

@WebServlet(urlPatterns = {"", "/matches", "/new-match", "/match-score"})
@NoArgsConstructor
public class UiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getServletPath());
        System.out.println(req.getQueryString());
        switch (req.getServletPath()) {
            case "/matches" -> req.getRequestDispatcher("/matches/matches.html").forward(req, resp);
            case "/new-match" -> req.getRequestDispatcher("/create_match/create-match.html").forward(req, resp);
            case "/match-score" -> req.getRequestDispatcher("/match_score/match-score.html").forward(req, resp);
            default -> req.getRequestDispatcher("/index/index.html").forward(req, resp);
        }
    }
}
