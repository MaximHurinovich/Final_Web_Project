package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class HorseDAO {
    private static final String SQL_SELECT_HORSES =
            "SELECT * FROM horseraces_db.horses WHERE race_number=?";
    private static final String SQL_ADD_HORSES =
            "INSERT INTO horseraces_db.horses VALUES(?,?,?)";
    private static final String SQL_ADD_RESULT_HORSES =
            "INSERT INTO horseraces_db.history_horses VALUES(?,?,?,?)";
    private static final String SQL_PREVIOUS_ID_HORSE =
            "SELECT MAX(id_horse) FROM horseraces_db.horses";
    private ProxyConnection connection = null;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ArrayList<Horse> getHorses(int race) throws DAOException {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horse;
        connection = pool.createConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_HORSES);
            statement.setString(1, String.valueOf(race));
            ResultSet resultSet = statement.executeQuery();
            BetDAO betDAO = new BetDAO();
            ArrayList<Bet> bets = betDAO.getBets();
            int i = 0;
            while (resultSet.next()) {
                Bet temp = null;
                for (Bet bet : bets) {
                    if (bet.getHorseID() == resultSet.getInt(1)) {
                        temp = bet;
                        break;
                    }
                }
                if (temp == null) {
                    return null;
                }
                horse = new Horse(resultSet.getInt(2), resultSet.getString(3), temp);
                horse.setHorseId(resultSet.getInt(1));
                horses.add(horse);
                i++;
            }
            statement.close();
            return horses;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    ArrayList<Horse> getBookmakerHorses(int race) throws DAOException {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horse;
        connection = pool.createConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT_HORSES);
            statement.setString(1, String.valueOf(race));
            ResultSet resultSet = statement.executeQuery();
            BetDAO betDAO = new BetDAO();
            ArrayList<Bet> bets = betDAO.getBets();
            while (resultSet.next()) {
                Bet temp = null;
                for (Bet bet : bets) {
                    if (bet.getHorseID() == resultSet.getInt(1)) {
                        temp = bet;
                        break;
                    }
                }
                if (temp == null) {
                    horse = new Horse(resultSet.getInt(2), resultSet.getString(3), null);
                    horse.setHorseId(resultSet.getInt(1));
                    horses.add(horse);
                } else {
                    return null;
                }
            }
            statement.close();
            return horses;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            if (connection != null)
                connection.close();
        }

    }

    public boolean addHorses(int raceId, HashSet<String> names) throws DAOException {
        connection = pool.createConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_PREVIOUS_ID_HORSE);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int horseId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement(SQL_ADD_HORSES);
            preparedStatement.setInt(2, raceId);
            int result = 0;
            for (String name : names) {
                preparedStatement.setInt(1, ++horseId);
                preparedStatement.setString(3, name);
                result = preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    public void addResultHorses(ArrayList<Horse> horses, int resultId) throws DAOException {
        connection = pool.createConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_RESULT_HORSES);
            preparedStatement.setInt(3, resultId);
            for (Horse horse : horses) {
                preparedStatement.setInt(1, horse.getHorseId());
                preparedStatement.setString(2, horse.getName());
                preparedStatement.setInt(4, horse.getPlace());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            if (connection != null)
                connection.close();
        }

    }
}
