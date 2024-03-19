package it.learning.project.jakartajsp.controller;

import it.learning.project.jakartajsp.dao.UserDAO;
import it.learning.project.jakartajsp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtain the User Identifier
        String userId = request.getParameter("userId");
        UserDAO userDAO = new UserDAO();

        try {
            // Perform the Deletion
            // Ensure userID is not null, empty, and is a valid number
            if (userId != null && !userId.trim().isEmpty()) {
                int id = Integer.parseInt(userId);  // Ensure this is a valid integer
                userDAO.deleteUser(id);  // Implement deleteUser in your DAO
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
