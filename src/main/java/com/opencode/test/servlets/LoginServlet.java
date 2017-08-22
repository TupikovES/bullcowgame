package com.opencode.test.servlets;

import com.opencode.test.dao.UserDao;
import com.opencode.test.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserDao userDao = UserDao.getInstance();
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession();

        String path = req.getServletPath();
        System.out.println(path);

        if (path.equals("/login")) {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        } else if (path.equals("/logout")) {
            if (session.getAttribute("user") != null) {
                session.invalidate();
            }
            resp.sendRedirect("/game/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String pass = req.getParameter("pass");

        session = req.getSession();

        User user = userDao.get(login, pass);

        if (user == null) {
            req.setAttribute("Message", "Incorrect. Please try again");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }

        session.setAttribute("user", user);
        req.setAttribute("user", session.getAttribute("user"));
        resp.sendRedirect("/game");
    }
}
