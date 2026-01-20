package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/finishTask.do")
public class FinishTaskServlet extends HttpServlet implements com.dao.Dao {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"), second = request.getParameter("second");
        int result = request.getParameter("result").isEmpty() ? 0 : Integer.parseInt(request.getParameter("result"));
        try {
            String sql = "select start, ending from programs where projid = (select projid from platforms where userid = ?)";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                if (LocalDate.now().isAfter(rs.getDate(1).toLocalDate()) && LocalDate.now().isBefore(rs.getDate(2).toLocalDate()))
                    updater(id, second, result);
                else
                    response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/users_index.jsp").forward(request, response);
    }
    public void updater(String id, String second, int result) throws SQLException, NamingException {
        String sql = "insert into users(userid, secondhand, result) values (?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, second);
        ps.setInt(3, result);
        ps.executeUpdate();
    }
}
