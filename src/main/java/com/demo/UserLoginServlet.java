package com.demo;

import com.dao.Dao;
import com.dao.UserDaoImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userLogin.do")
public class UserLoginServlet extends HttpServlet implements Dao{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users_index.jsp").forward(request, response);
        UserDaoImp userDaoImp = new UserDaoImp();
        try {
            String name = userDaoImp.findById(Integer.parseInt(request.getParameter("id")));
            if (name.equals(request.getParameter("name"))) {
                String sql = "select projid from platforms where userid = ?";
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ResultSet rs = ps.executeQuery();
                int projid = 0;
                if (rs.next())
                    rs.getInt(1);
                sql = "select project, document from programs where projid = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, projid);
                rs = ps.executeQuery();
                String projname = rs.getString(1);
                InputStream fis = rs.getBlob(2).getBinaryStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int length;
                byte[] buffer = new byte[1024];
                while ((length = fis.read(buffer)) != -1)
                    baos.write(buffer, 0, length);
                baos.flush();
                XWPFDocument document = new XWPFDocument();
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.createRun().setText(baos.toString());
                FileOutputStream out = new FileOutputStream("output.docx");
                document.write(out);
                out.close();
                sql = "select department from divisions where projid = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, projid);
                rs = ps.executeQuery();
                String depname = rs.getString(1);
                sql = "select secondhand, result, answer from users where userid = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                rs = ps.executeQuery();
                List<String> secondhand = new ArrayList<>(), result = new ArrayList<>(), answer = new ArrayList<>();
                while (rs.next()) {
                    secondhand.add(rs.getString(1));
                    result.add(rs.getString(2));
                    answer.add(rs.getString(3));
                }
                request.setAttribute("depname", depname);
                request.setAttribute("projname", projname);
                request.setAttribute("secondhand", secondhand);
                request.setAttribute("result", result);
                request.setAttribute("answer", answer);
                request.setAttribute("id", request.getParameter("id"));
                getServletContext().getRequestDispatcher("users_index.jsp").forward(request, response);
            }
            else
                response.sendRedirect("index.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
