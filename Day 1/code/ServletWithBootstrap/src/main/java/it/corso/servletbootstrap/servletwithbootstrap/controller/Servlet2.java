package it.corso.servletbootstrap.servletwithbootstrap.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "servlet2", value = "/servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspPage = "name.jsp";
        String name = "Alessandro";
        request.setAttribute("name",name);
        RequestDispatcher view = request.getRequestDispatcher(jspPage);
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspPage = "name.jsp";
        String name = "Alessandro";
        String param = "";
        request.getRequestDispatcher(param);
        request.setAttribute("name",name);

        RequestDispatcher view = request.getRequestDispatcher(jspPage);
        view.forward(request, response);
    }

}
