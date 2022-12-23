package it.corso.esempiopagine.esempio2.controller.User;

import it.corso.esempiopagine.esempio2.dao.UserDAO;
import it.corso.esempiopagine.esempio2.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletInserUser", value = "/ServletInserUser")
public class ServletInserUser extends HttpServlet {
    private UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        Integer age = Integer.parseInt(request.getParameter("age"));
        User newUser = new User(name, email, country,age);
        String page = "userInserito.jsp";
        try {
            userDAO.insertUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        }
        response.sendRedirect(page);
    }
}
