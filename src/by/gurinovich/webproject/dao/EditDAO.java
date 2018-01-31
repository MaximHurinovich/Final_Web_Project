package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditDAO {
        private static final String SQL_UPDATE_PROFILE =
                "UPDATE horseraces_db.personal_info SET first_name=?, second_name=?, email=?, id_card=? WHERE username=?";
        private ProxyConnection connection = null;
    private ConnectionPool pool = ConnectionPool.getInstance();

        public EditDAO() {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public boolean updateProfile(String userName, String firstName, String secondName, String email, String cardNumber){
            try {
                connection = pool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PROFILE);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, cardNumber);
                preparedStatement.setString(5, userName);
                int i = preparedStatement.executeUpdate();
                return i>0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
}
