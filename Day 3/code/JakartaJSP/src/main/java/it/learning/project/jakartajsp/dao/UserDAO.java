package it.learning.project.jakartajsp.dao;



import it.learning.project.jakartajsp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:8889/demo?serverTimezone=UTC";
    private String jdbcUsername = "diemme";
    private String jdbcPassword = "password";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        System.out.println("Connesso al DB");
        return connection;
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException {
        System.out.println(INSERT_USERS_SQL);

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getCountry());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    public User selectUser(int id) throws SQLException, ClassNotFoundException {
        User user = null;
        // Step 1: Establishing a Connection
        Connection connection = getConnection();
        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            user = new User(id, name, email, country);
        }
        return user;
    }

    public List<User> selectAllUsers() throws SQLException, ClassNotFoundException {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        Connection connection = getConnection();

        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            users.add(new User(id, name, email, country));
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getCountry());
        statement.setInt(4, user.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

}
