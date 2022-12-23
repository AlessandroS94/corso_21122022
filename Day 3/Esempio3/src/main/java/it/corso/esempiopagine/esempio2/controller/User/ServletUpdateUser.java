package it.corso.esempiopagine.esempio2.controller.User;

import it.corso.esempiopagine.esempio2.dao.UserDAO;
import it.corso.esempiopagine.esempio2.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletUpdateUser", value = "/ServletUpdateUser")
public class ServletUpdateUser extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String page = "userUpdate.jsp";
        User existingUser = null;
        try {
            existingUser = userDAO.selectUser(id);
        }  catch (SQLException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (!existingUser.equals(null))
            request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        Integer age = Integer.parseInt(request.getParameter("age"));
        User newUser = new User(id,name, email, country,age);
        String page = "userUpdated.jsp";
        try {
            userDAO.updateUser(newUser);
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
