package com.opencode.test.servlets;

import com.opencode.test.dao.HistoryDao;
import com.opencode.test.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private HistoryDao historyDao = HistoryDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/game");
            return;
        }

        request.setAttribute("histories", historyDao.getHistoryUser(user.getId()));
        request.getRequestDispatcher("/WEB-INF/pages/history.jsp").forward(request, response);
    }
}
