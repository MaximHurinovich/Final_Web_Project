package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;

import java.sql.*;

public class CardDAO {
    private static final String SQL_SELECT_CARD =
            "SELECT amount FROM horseraces_db.bank_imitation WHERE card_number=?";
    private ProxyConnection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public CardDAO() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getCardAmount(String cardNumber){
        double amount = 0.0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CARD);
            preparedStatement.setString(1, cardNumber);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            amount = resultSet.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return amount;
    }
}
