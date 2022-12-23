package it.corso.esempiopagine.esempio2.dao;

import it.corso.esempiopagine.esempio2.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country,age) VALUES (?, ?, ?,?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country,age from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =?, age = ? where id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLSyntaxErrorException, SQLException, ClassNotFoundException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getCountry());
        preparedStatement.setInt(4, user.getAge());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();

    }

    public User selectUser(int id) throws SQLSyntaxErrorException, SQLException, ClassNotFoundException {
        User user = null;
        Connection connection = getConnection();
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
            int age = rs.getInt("age");
            user = new User(id, name, email, country, age);
        }
        return user;
    }

    public List<User> selectAllUsers() throws SQLSyntaxErrorException, SQLException, ClassNotFoundException {

        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            int age = rs.getInt("age");
            users.add(new User(id, name, email, country, age));
        }

        return users;
    }

    public void deleteUser(int id) throws SQLSyntaxErrorException, SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void updateUser(User user) throws SQLSyntaxErrorException, SQLException, ClassNotFoundException {

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
        System.out.println(statement);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getCountry());
        statement.setInt(4, user.getAge());
        statement.setInt(5, user.getId());
        System.out.println(statement);
        statement.executeUpdate();
    }


}
