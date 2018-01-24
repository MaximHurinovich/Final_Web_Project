package by.gurinovich.webproject.dao;

import java.sql.*;

public class AuthenticationDAO {
    private static final String SQL_SELECT_USER =
            "SELECT * FROM horseraces_db.personal_info WHERE username =? AND password =?";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;

    public AuthenticationDAO(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean authenticateUser(String login, String password) {

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }
        return false;
    }


    public String userName(String login, String password) {
        StringBuilder buffer = new StringBuilder();
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            buffer.append(resultSet.getString(5)).append(" ").append(resultSet.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }
        return buffer.toString();

    }
}