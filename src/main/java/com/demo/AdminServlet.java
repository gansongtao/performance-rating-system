package com.demo;

import com.model.Curriculum;
import com.model.Ranking;
import com.model.Region;
import com.model.School;
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

@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet implements com.dao.Dao {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String sql;
        School[] schools = new School[100];
        switch (req.getParameter("search")) {
            case "all_class":
                sql = "select * from all_classes where gst_stuid=?";
                try {
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, req.getParameter("stuID"));
                    ResultSet rs = ps.executeQuery();
                    String result = "";
                    if (rs.next())
                        result = rs.getString("courses") + " " + rs.getString("credits");
                    req.setAttribute("result", result);
                    getServletContext().getRequestDispatcher("/all_classes.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "avg_class":
                Curriculum[] curriculum = new Curriculum[100];
                sql = "select * from curriculum_average";
                try {
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        curriculum[i].setGst_course(rs.getString(1));
                        curriculum[i].setColumn(Integer.parseInt(rs.getString(2)));
                    }
                    req.setAttribute("curriculum", curriculum);
                    getServletContext().getRequestDispatcher("/avg_class.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "region":
                Region[] regions = new Region[100];
                sql = "select * from regions";
                try {
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        regions[i].setRegion(rs.getString(1));
                        regions[i].setCount(Integer.parseInt(rs.getString(2)));
                    }
                    req.setAttribute("regions", regions);
                    getServletContext().getRequestDispatcher("/region.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "one":
                sql = "select * from stu_average1";
                try {
                    Schooling(req, resp, sql, schools);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "two":
                sql = "select * from stu_average2";
                try {
                    Schooling(req, resp, sql, schools);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "ranking":
                sql = "select * from score_ranking";
                Ranking[] rankings = new Ranking[100];
                try {
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        rankings[i].setId(Integer.parseInt(rs.getString(1)));
                        rankings[i].setColumn(Integer.parseInt(rs.getString(2)));
                    }
                    req.setAttribute("rankings", rankings);
                    getServletContext().getRequestDispatcher("/ranking.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void Schooling(HttpServletRequest req, HttpServletResponse resp, String sql, School[] schools) throws SQLException, NamingException, ServletException, IOException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        while (rs.next()) {
            schools[i].setStuid(Integer.parseInt(rs.getString(1)));
            schools[i].setColumn(Integer.parseInt(rs.getString(2)));
            i++;
        }
        req.setAttribute("schools", schools);

        getServletContext().getRequestDispatcher("/one.jsp").forward(req, resp);
    }
}
