package com.demo;

import com.dao.ProjDaoImp;
import com.model.Proj;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/updateProject.do")
public class ProjectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjDaoImp dao = new ProjDaoImp();
        Proj proj = new Proj();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        InputStream fis = request.getPart("file").getInputStream();
        proj.setName(name);
        proj.setId(id);
        proj.setDoc(fis);
        String message;
        switch (request.getParameter("change")) {
            case "add":
                try {
                    dao.addProj(proj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    dao.deleteProj(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    dao.updateProj(id, Integer.parseInt(request.getParameter("depid")));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    proj = dao.findProjById(id);
                    if (proj != null)
                        message = "id: " + proj.getId() + " name: " + proj.getName();
                    else
                        message = "Project not found";
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("result", message);
                getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/admin_index.jsp").forward(request, response);
    }
}
