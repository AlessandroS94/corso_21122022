package it.corso.esempiopagine.esempio2.controller.User;

import it.corso.esempiopagine.esempio2.dao.UserDAO;
import it.corso.esempiopagine.esempio2.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletShowUsers", value = "/ServletShowUsers")
public class ServletShowUsers extends HttpServlet {
    private UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = null;
        String page = "userList.jsp";
        try {
            listUser = userDAO.selectAllUsers();
            request.setAttribute("listUser", listUser);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
            page = "error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
