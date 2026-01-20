package com.demo;

import com.dao.IndexDaoImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateindex.do")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexDaoImp indexDaoImp = new IndexDaoImp();
        String first = request.getParameter("first"), second = request.getParameter("second"), message = "";
        switch (request.getParameter("change")) {
            case "add":
                try {
                    indexDaoImp.addIndex(second, first);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "del2":
                try {
                    indexDaoImp.deleteSecond(second);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "del1":
                try {
                    indexDaoImp.deleteFirst(first);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    String[] results = indexDaoImp.getByFirst(first);
                    for (String result : results)
                        message = message.concat(result + " ");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("result", message);
                getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/admin_index.jsp").forward(request, response);
    }
}
