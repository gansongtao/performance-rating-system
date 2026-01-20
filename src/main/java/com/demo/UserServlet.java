package com.demo;

import com.dao.UserDaoImp;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateUser.do")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImp dao = new UserDaoImp();
        User user = new User();
        String message;
        int id = Integer.parseInt(request.getParameter("id"));
        user.setId(id);
        user.setName(request.getParameter("name"));
        switch (request.getParameter("change")) {
            case "add":
                try {
                    dao.addUser(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    dao.deleteUser(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    dao.updateUser(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    message = dao.findById(id);
                    if (message == null)
                        message = "User not found";
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("result", message);
                getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/admin_index.jsp").forward(request, response);
    }
}
