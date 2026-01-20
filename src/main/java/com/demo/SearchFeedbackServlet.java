package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/searchFeedback.do")
public class SearchFeedbackServlet extends HttpServlet implements com.dao.Dao {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"), second = request.getParameter("second"), sql = "select result, answer from users where username = ? and secondhand = ?", message = "";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, second);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                message = rs.getString(1) + " " + rs.getString(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("result", message);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}
