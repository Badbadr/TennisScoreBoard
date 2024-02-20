package org.example.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.repository.config.HibernateConfig;
import org.example.repository.jpa.MatchRepository;
import org.example.repository.jpa.PlayerRepository;
import org.example.repository.redis.OngoingMatchRepository;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.OngoingMatchService;

@WebListener
public class ServletContextConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        PlayerRepository playerRepository = new PlayerRepository();
        MatchRepository matchRepository = new MatchRepository();

        HibernateConfig.getSessionFactory();
        FinishedMatchesPersistenceService finishedMatchesPersistenceService =
                new FinishedMatchesPersistenceService(matchRepository, playerRepository);

        OngoingMatchService matchService = new OngoingMatchService(
                finishedMatchesPersistenceService,
                new OngoingMatchRepository(),
                playerRepository
        );

        ctx.setAttribute("ongoingMatchService", matchService);
        ctx.setAttribute("finishedMatchesPersistenceService", finishedMatchesPersistenceService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
