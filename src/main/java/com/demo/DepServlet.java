package com.demo;

import com.dao.DepDaoImp;
import com.model.Dep;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/updateDep.do")
public class DepServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepDaoImp dao = new DepDaoImp();
        Dep dep = new Dep();
        int id = Integer.parseInt(request.getParameter("id"));
        String message = "";
        dep.setId(id);
        dep.setName(request.getParameter("name"));
        switch (request.getParameter("change")) {
            case "add":
                try {
                    dao.addDep(dep, Integer.parseInt(request.getParameter("projid")));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    dao.deleteDep(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    dao.updateDep(dep);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    dep = dao.findById(id);
                    if (dep != null)
                        message = "id: " + dep.getId() + " name: " + dep.getName();
                    else
                        message = "Department not found";
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("result", message);
                getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
                break;
            case "all":
                try {
                    List<Integer> user = dao.findAllUsers(id);
                    if (user != null)
                        for (int i : user)
                            message = message.concat(i + " ");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("result", message);
                getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/admin_index.jsp").forward(request, response);
    }
}
