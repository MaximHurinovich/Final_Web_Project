package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;

public class CardDAO {
    private static final String SQL_SELECT_CARD =
            "SELECT amount FROM horseraces_db.bank_imitation WHERE card_number=?";
    private static final String SQL_SELECT_PROFILE_AMOUNT =
            "SELECT amount FROM horseraces_db.personal_info WHERE id_card=?";
    private static final String SQL_UPDATE_MONEY_PROFILE =
            "UPDATE horseraces_db.personal_info SET amount=ROUND(?,2) WHERE id_card=?";
    private static final String SQL_UPDATE_MONEY_CARD =
            "UPDATE horseraces_db.bank_imitation SET amount=ROUND(?,2) WHERE card_number=?";
    private static final String SQL_UPDATE_MONEY_BET =
            "UPDATE horseraces_db.personal_info SET amount=ROUND(?,2) WHERE username=?";
    private ProxyConnection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public double getAccountAmount(String cardNumber) throws SQLException {
        double amount;
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_SELECT_PROFILE_AMOUNT);
        preparedStatement.setString(1, cardNumber);
        resultSet = preparedStatement.executeQuery();

        resultSet.last();
        amount = resultSet.getDouble(1);
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null)
            connection.close();

        return amount;
    }

    public double getCardAmount(String cardNumber) throws SQLException {
        double amount;
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_SELECT_CARD);
        preparedStatement.setString(1, cardNumber);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        amount = resultSet.getDouble(1);
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null)
            connection.close();

        return amount;
    }

    public boolean updateCardToAccount(String cardNumber, double amount, double sum, double accountSum) throws SQLException {
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_UPDATE_MONEY_CARD);
        preparedStatement.setDouble(1, amount - sum);
        preparedStatement.setString(2, cardNumber);
        int i = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(SQL_UPDATE_MONEY_PROFILE);
        preparedStatement.setDouble(1, accountSum + sum);
        preparedStatement.setString(2, cardNumber);
        int y = preparedStatement.executeUpdate();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        return i > 0 && y > 0;
    }

    public boolean updateAccountToCard(String cardNumber, double amount, double sum, double accountSum) throws SQLException {
        return updateCardToAccount(cardNumber, amount, -sum, accountSum);
    }

    public boolean updateAccoundBet(String userName, double currentAmount, double bet) throws SQLException {
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_UPDATE_MONEY_BET);
        preparedStatement.setDouble(1, currentAmount - bet);
        preparedStatement.setString(2, userName);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        if (connection != null)
            connection.close();
        return i > 0;
    }
}
