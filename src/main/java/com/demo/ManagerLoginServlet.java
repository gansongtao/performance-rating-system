package com.demo;

import com.dao.AdminDaoImp;
import com.model.Admin;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/managerLogin.do")
public class ManagerLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AdminDaoImp impl = new AdminDaoImp();
        try {
            Admin admin = impl.findById(Integer.parseInt(request.getParameter("id")));
            if (admin.getName().equals(request.getParameter("name")))
                response.sendRedirect("/admin_index.jsp");
            else
                response.sendRedirect("index.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
