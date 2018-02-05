package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.*;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.util.IDGenerator;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;
import by.gurinovich.webproject.util.Constant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class UserDAO {
    private final String SQL_SELECT_USER =
            "SELECT * FROM horseraces_db.personal_info WHERE username =? AND password =?";
    private final String SQL_SELECT_AMOUNT =
            "SELECT amount FROM horseraces_db.personal_info WHERE username =?";
    private final String SQL_BAN_USER =
            "UPDATE horseraces_db.personal_info SET is_banned='1' WHERE username =?";
    private final String SQL_MAKE_ADMIN =
            "UPDATE horseraces_db.personal_info SET role='a',id_card=NULL,amount=NULL WHERE username=?";
    private final String SQL_MAKE_BOOKMAKER =
            "UPDATE horseraces_db.personal_info SET role='b',id_card=NULL,amount=NULL,id_bookmaker=? WHERE username=?";
    private final String SQL_USER_WIN =
            "UPDATE horseraces_db.personal_info SET amount=ROUND(?,2) WHERE username =?";
    private ProxyConnection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public boolean authenticateUser(String login, String password) throws DAOException {
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        boolean check = resultSet.next();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return check;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }

    }


    public String userName(String login, String password) throws DAOException {
        StringBuilder buffer = new StringBuilder();
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        buffer.append(resultSet.getString(4)).append(" ").append(resultSet.getString(5));

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return buffer.toString();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public Person createUser(String userName, String password) throws DAOException {
        Person user;
        String firstName, secondName, cardNumber, email;
        double amount;

        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        String role = resultSet.getString(9);
        firstName = resultSet.getString(4);
        secondName = resultSet.getString(5);
        email = resultSet.getString(6);
        boolean isBanned = !Constant.SQL_FALSE.equals(resultSet.getString(10));
        if (Constant.USER_ROLE.equals(role)) {
            amount = resultSet.getDouble(7);
            cardNumber = resultSet.getString(2);
            user = new User(userName, password, firstName, secondName, cardNumber, email, amount);
        } else if (Constant.ADMIN_ROLE.equals(role)) {
            user = new Admin(userName, password, firstName, secondName, email);
        } else {
            int bookmakerId = resultSet.getInt(3);
            user = new Bookmaker(userName, password, firstName, secondName, email, bookmakerId);
        }
        user.setRole(role);
        user.setBanned(isBanned);
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return user;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(),e);
        }finally {
            connection.close();
        }
    }

    public ArrayList<Person> getUsers() throws DAOException {
        ArrayList<Person> users = new ArrayList<>();
        String username, firstName, secondName, email, password, role;
        Person user;
        connection = pool.createConnection();
        String SQL_SELECT_ALL_USERS = "SELECT * FROM horseraces_db.personal_info WHERE role='u' AND is_banned='0'";
        Statement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
        resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
        while (resultSet.next()) {
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
        return users;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean banUser(String username) throws DAOException {
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_BAN_USER);
        preparedStatement.setString(1, username);
        int result = preparedStatement.executeUpdate();
        if(preparedStatement!=null) {
            preparedStatement.close();
        }
        return result != 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean makeAdmin(String username) throws DAOException {
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_MAKE_ADMIN);
        preparedStatement.setString(1, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result != 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            connection.close();
        }
    }

    public boolean makeBookmaker(String username) throws DAOException {
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_MAKE_BOOKMAKER);
        preparedStatement.setInt(1, IDGenerator.generateID()+ new Random().nextInt(1000));
        preparedStatement.setString(2, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result != 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection!=null) {
                connection.close();
            }
        }


    }

    public boolean winningBet(String username, double odd, int horseId, String type) throws DAOException {
        BetDAO betDAO = new BetDAO();
        Bet bet;
        bet = betDAO.getBet(horseId);
        double win = odd;
        if (Constant.SQL_WINNER_BET.equals(type)) {
            win *= bet.getWinner();
        } else if (Constant.SQL_TOP3_BET.equals(type)) {
            win *= bet.getTop3();
        } else {
            win *= bet.getOutsider();
        }
        connection = pool.createConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_AMOUNT);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double currentAmount = resultSet.getDouble(1);
        preparedStatement = connection.prepareStatement(SQL_USER_WIN);
        preparedStatement.setDouble(1, currentAmount + win);
        preparedStatement.setString(2, username);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result > 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}