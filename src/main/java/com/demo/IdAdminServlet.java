package com.demo;

import com.dao.AdminDaoImp;
import com.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/searchIdAdmin.do")
public class IdAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDaoImp dao = new AdminDaoImp();
        Admin admin;
        String message;
        try {
            admin = dao.findById(Integer.parseInt(request.getParameter("id")));
            if (admin != null)
                message = "id: " + admin.getId() + " name: " + admin.getName();
            else
                message = "Admin not found";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }
}
