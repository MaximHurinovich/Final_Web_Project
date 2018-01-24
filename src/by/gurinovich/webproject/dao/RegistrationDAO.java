package by.gurinovich.webproject.dao;

import java.sql.*;

public class RegistrationDAO {

    private static final String SQL_CREATE_USER =
            "INSERT INTO horseraces_db.personal_info VALUES(?,?,NULL,NULL,?,?,?,0,?,'u')";
    private static final String SQL_CHECK_USER =
            "SELECT * FROM horseraces_db.personal_info WHERE username=?";
    private static final String SQL_CHECK_CARD =
            "SELECT * FROM horseraces_db.bank_imitation WHERE card_number=? AND card_password=?";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public RegistrationDAO(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        try {
            if(checkUserName(userName) && checkCard(cardNumber, cardPassword)) {
                connection = ConnectionDB.getConnection();
                preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, cardNumber);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, secondName);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, password);
                int i = preparedStatement.executeUpdate();
                return i>0;
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

    private boolean checkUserName(String userName){
        try{
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_USER);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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

    public boolean checkCard(String cardNumber, String cardPassword) {
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_CARD);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, cardPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

}

