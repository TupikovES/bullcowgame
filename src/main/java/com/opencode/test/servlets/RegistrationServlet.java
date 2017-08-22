package com.opencode.test.servlets;

import com.opencode.test.dao.UserDao;
import com.opencode.test.database.Database;
import com.opencode.test.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("name");
        String pass = req.getParameter("pass");

        HttpSession session = req.getSession();

        int i = userDao.save(login, pass);

        if (i != -1) {
            User user = userDao.get(login, pass);
            System.out.println(user.getId());
            session.setAttribute("user", user);
            req.setAttribute("user", session.getAttribute("user"));
            resp.sendRedirect("/game");
        } else {
            req.setAttribute("message", "Error registration");
            req.getRequestDispatcher("/WEB-INF/pages/registration/registration.jsp").forward(req, resp);
        }
    }
}
