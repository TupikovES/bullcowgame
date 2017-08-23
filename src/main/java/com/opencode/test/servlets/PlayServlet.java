package com.opencode.test.servlets;

import com.opencode.test.dao.HistoryDao;
import com.opencode.test.dao.RatingDao;
import com.opencode.test.entity.Game;
import com.opencode.test.entity.History;
import com.opencode.test.entity.User;
import com.opencode.test.validate.Validate;
import com.opencode.test.validate.ValidateGameNumber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class PlayServlet extends HttpServlet {

    private HistoryDao historyDao = HistoryDao.getInstance();
    private RatingDao ratingDao = RatingDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/game/login");
        } else {
            Game game = (Game) session.getAttribute("game");
            if (game == null || game.isEndGame()) {
                game = new Game();
                session.setAttribute("game", game);
            }
            req.getRequestDispatcher("/WEB-INF/pages/game.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Validate validate = new ValidateGameNumber();

        HttpSession session = req.getSession();

        Game game = (Game) session.getAttribute("game");

        String playerNumbers = req.getParameter("playerNumber");
        if (playerNumbers == null) {
            playerNumbers = "0000";
        }
        if (validate.validate(playerNumbers)) {

            String result = game.check(playerNumbers);
            req.setAttribute("count", game.getCountStep());

            if (game.isEndGame()) {
                req.setAttribute("endGame", game.isEndGame());
                game.getHistory().add("done!");
                User user = (User) session.getAttribute("user");
                History history = new History(user.getId(), game.getCountStep(), new Date(System.currentTimeMillis()));
                historyDao.saveHistory(history);
                ratingDao.updateRating(user);
            }

        } else {
            req.setAttribute("message", "incorrect number");
        }

        req.getRequestDispatcher("/WEB-INF/pages/game.jsp").forward(req, resp);
    }
}
