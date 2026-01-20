package com.demo;

import com.dao.AdminDaoImp;
import com.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteAdmin.do")
public class DeleteAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDaoImp dao = new AdminDaoImp();
        Admin admin = new Admin();
        String message;
        admin.setId(Integer.parseInt(request.getParameter("id")));
        try {
            boolean success = dao.deleteAdmin(admin);
            if (success)
                message = "<li>Admin deleted successfully</li>";
            else
                message = "<li>Admin could not be deleted</li>";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("result", message);
        getServletContext().getRequestDispatcher("/deleteAdmin.jsp").forward(request, response);
    }
}
