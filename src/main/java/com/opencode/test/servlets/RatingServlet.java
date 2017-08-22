package com.opencode.test.servlets;

import com.opencode.test.dao.RatingDao;
import com.opencode.test.dao.UserDao;
import com.opencode.test.entity.Rating;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RatingServlet extends HttpServlet {

    private RatingDao ratingDao = RatingDao.getInstance();
    private UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Rating> ratings = ratingDao.getAll();
        for (Rating rating : ratings) {
            rating.setUserName(userDao.getById(rating.getUser_id()).getLogin());
        }
        req.setAttribute("ratings", ratings);
        req.getRequestDispatcher("/WEB-INF/pages/rating.jsp").forward(req, resp);
    }
}
