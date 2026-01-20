package com.demo;

import com.dao.AdminDaoImp;
import com.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addAdmin.do")
public class AddAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDaoImp dao = new AdminDaoImp();
        Admin admin = new Admin();
        String message;
        admin.setId(Integer.parseInt(request.getParameter("id")));
        admin.setName(request.getParameter("name"));
        try {
            boolean success = dao.addAdmin(admin);
            if (success)
                message = "<li>Admin added successfully</li>";
            else
                message = "<li>Admin could not be added</li>";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/addAdmin.jsp").forward(request, response);
    }
}
