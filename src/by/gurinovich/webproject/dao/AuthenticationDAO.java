package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;

public class AuthenticationDAO {
    private final String SQL_SELECT_USER =
            "SELECT * FROM horseraces_db.personal_info WHERE username =? AND password =?";

    private ProxyConnection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public boolean authenticateUser(String login, String password) throws SQLException {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
            if (connection != null) {

                    connection.close();

            }
        return false;
    }


    public String userName(String login, String password) throws SQLException {
        StringBuilder buffer = new StringBuilder();

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            buffer.append(resultSet.getString(5)).append(" ").append(resultSet.getString(6));

        if(preparedStatement!=null){
            preparedStatement.close();
        }
            if (connection != null) {
                    connection.close();

            }

        return buffer.toString();

    }

    public User createUser(String userName, String password) throws SQLException {
        User user = null;
        String firstName, secondName, cardNumber, email;
        double amount;

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            resultSet.last();
            firstName = resultSet.getString(5);
            secondName = resultSet.getString(6);
            email = resultSet.getString(7);
            amount = resultSet.getDouble(8);
            cardNumber = resultSet.getString(2);
            user = new User(userName, password, firstName, secondName, cardNumber, email, amount);
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        return user;
    }
}