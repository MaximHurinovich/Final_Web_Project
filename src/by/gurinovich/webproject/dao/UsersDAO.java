package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Admin;
import by.gurinovich.webproject.entity.Bookmaker;
import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.id.IDGenerator;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO {
    private final String SQL_SELECT_USER =
            "SELECT * FROM horseraces_db.personal_info WHERE username =? AND password =?";
    private final String SQL_BAN_USER =
            "UPDATE horseraces_db.personal_info SET is_banned='1' WHERE username =?";
    private final String SQL_MAKE_ADMIN =
            "UPDATE horseraces_db.personal_info SET role='a',id_card=NULL,amount=NULL WHERE username=?";
    private final String SQL_MAKE_BOOKMAKER =
            "UPDATE horseraces_db.personal_info SET role='b',id_card=NULL,amount=NULL,id_bookmaker=? WHERE username=?";
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
            boolean check = resultSet.next();
        if(preparedStatement!=null){
            preparedStatement.close();
        }
            if (connection != null) {

                    connection.close();

            }
        return check;

    }


    public String userName(String login, String password) throws SQLException {
        StringBuilder buffer = new StringBuilder();

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            buffer.append(resultSet.getString(4)).append(" ").append(resultSet.getString(5));

        if(preparedStatement!=null){
            preparedStatement.close();
        }
            if (connection != null) {
                    connection.close();

            }

        return buffer.toString();

    }

    public Person createUser(String userName, String password) throws SQLException {
        Person user = null;
        String firstName, secondName, cardNumber, email;
        double amount;

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            String role = resultSet.getString(9);
            firstName = resultSet.getString(4);
            secondName = resultSet.getString(5);
            email = resultSet.getString(6);
            boolean isBanned = !"0".equals(resultSet.getString(10));
            if("u".equals(role)) {
                amount = resultSet.getDouble(7);
                cardNumber = resultSet.getString(2);
                user = new User(userName, password, firstName, secondName, cardNumber, email, amount);
            }else if("a".equals(role)){
                user = new Admin(userName, password, firstName, secondName, email);
            }else{
                int bookmakerId = resultSet.getInt(3);
                user = new Bookmaker(userName, password, firstName, secondName, email, bookmakerId);
            }
            user.setBanned(isBanned);
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        connection.close();
        return user;
    }

    public ArrayList<Person> getUsers() throws SQLException{
        ArrayList<Person> users = new ArrayList<>();
        String username, firstName, secondName, email, password, role;
        Person user;
        connection = pool.getConnection();
        String SQL_SELECT_ALL_USERS = "SELECT * FROM horseraces_db.personal_info WHERE role='u' AND is_banned='0'";
        Statement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
        resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
        while (resultSet.next()){
            username = resultSet.getString(1);
            firstName = resultSet.getString(4);
            secondName = resultSet.getString(5);
            email = resultSet.getString(6);
            role = resultSet.getString(9);
            password = resultSet.getString(8);
            String cardNumber = resultSet.getString(2);
            double amount = resultSet.getDouble(7);
            user = new User(username, password, firstName, secondName, cardNumber, email, amount);
            user.setRole(role);
            users.add(user);
        }
        statement.close();
        if(connection!=null){
            connection.close();
        }
        return users;
    }

    public boolean banUser(String username) throws SQLException{
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_BAN_USER);
        preparedStatement.setString(1, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        if(connection!=null){
            connection.close();
        }
        return result!=0;
    }

    public boolean makeAdmin(String username) throws SQLException{
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_MAKE_ADMIN);
        preparedStatement.setString(1, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result!=0;
    }

    public boolean makeBookmaker(String username) throws SQLException{
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_MAKE_BOOKMAKER);
        preparedStatement.setInt(1, IDGenerator.generateID());
        preparedStatement.setString(2, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result!=0;
    }
}