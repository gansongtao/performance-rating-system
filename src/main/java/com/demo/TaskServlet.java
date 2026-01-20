package com.demo;

import com.dao.RateDaoImp;
import com.model.Rate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/updateTask.do")
public class TaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RateDaoImp dao = new RateDaoImp();
        Rate rate = new Rate();
        int id = Integer.parseInt(request.getParameter("id"));
        Date start = Date.valueOf(LocalDate.parse(request.getParameter("startDate"))), end = Date.valueOf(LocalDate.parse(request.getParameter("endDate"))), oldStart =  Date.valueOf(request.getParameter("oldStartDate")), oldEnd = Date.valueOf(request.getParameter("oldEndDate"));
        String[] seconds = request.getParameter("second").split(",");
        String newSecond = request.getParameter("newSecond"), oldSecond = request.getParameter("oldSecond"), message = "";
        rate.setStart(start);
        rate.setEnd(end);
        rate.setId(id);
        rate.setSeconds(seconds);
        switch (request.getParameter("change")) {
            case "add":
                try {
                    dao.addRate(rate);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                if (LocalDate.now().isAfter(start.toLocalDate()))
                    response.sendRedirect("error.jsp");
                try {
                    dao.deleteRate(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                if (LocalDate.now().isAfter(start.toLocalDate()))
                    response.sendRedirect("error.jsp");
                try {
                    dao.updateRate(id, oldSecond, newSecond, oldStart, start, oldEnd, end);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    String[] results = dao.findRateById(id);
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
