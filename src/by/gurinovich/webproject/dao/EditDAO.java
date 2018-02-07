package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditDAO {
    private static final String SQL_UPDATE_PROFILE =
            "UPDATE horseraces_db.personal_info SET first_name=?, second_name=?, email=?, id_card=? WHERE username=?";
    private ConnectionPool pool = ConnectionPool.getInstance();

    public boolean updateProfile(String userName, String firstName, String secondName, String email, String cardNumber) throws DAOException {
        PreparedStatement preparedStatement = null;
        try (ProxyConnection connection = pool.createConnection()) {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PROFILE);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, cardNumber);
            preparedStatement.setString(5, userName);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i > 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        }
    }
}
