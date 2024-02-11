package org.example.rest.servlets;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Player;
import org.example.util.Mapper;

import java.io.IOException;

@WebServlet(urlPatterns = "/match")
public class MatchServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player player2 = Mapper.mapper.readValue(req.getParameter("player2"), Player.class);
        Player player1 = Mapper.mapper.readValue(req.getParameter("player1"), Player.class);
    }
}
