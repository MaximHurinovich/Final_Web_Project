package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.util.IDGenerator;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;
import by.gurinovich.webproject.util.Validator;

import java.sql.*;
import java.util.ArrayList;

public class RaceDAO {

    private static final String SQL_SELECT_RACE =
            "SELECT * FROM horseraces_db.race";
    private static final String SQL_SELECT_RACE_BY_ID =
            "SELECT * FROM horseraces_db.race WHERE idrace=?";
    private static final String SQL_SELECT_RESULT_BY_ID =
            "SELECT * FROM horseraces_db.history_race WHERE idrace=?";
    private static final String SQL_DELETE_ACTIVE_RACE =
            "DELETE FROM horseraces_db.race WHERE idrace=?";
    private static final String SQL_DELETE_ACTIVE_HORSES =
            "DELETE FROM horseraces_db.horses WHERE race_number=?";
    private static final String SQL_DELETE_BETS =
            "DELETE FROM horseraces_db.bets WHERE id_horse=?";
    private static final String SQL_ADD_RACE =
            "INSERT INTO horseraces_db.race VALUES(?,?,?)";
    private static final String SQL_ADD_RESULT_RACE =
            "INSERT INTO horseraces_db.history_race VALUES(?,?,?)";
    private static final String SQL_PREVIOUS_ID_RESULTS =
            "SELECT MAX(idrace) FROM horseraces_db.history_race";
    private static final String SQL_PREVIOUS_ID_HORSE =
            "SELECT MAX(idrace) FROM horseraces_db.race";
    private ProxyConnection connection = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ArrayList<Race> getRaces() throws DAOException {
        ArrayList<Race> races = new ArrayList<>();
        Race race;
        connection = pool.createConnection();
        Statement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT_RACE);
            resultSet = statement.executeQuery(SQL_SELECT_RACE);
            HorseDAO horseDAO = new HorseDAO();
            while (resultSet.next()) {
                int i = resultSet.getInt(1);
                ArrayList<Horse> horses = horseDAO.getHorses(i);
                if (horses == null) {
                    continue;
                }
                race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
                race.setId(resultSet.getInt(1));
                races.add(race);
            }
            statement.close();
            return races;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            connection.close();
        }

    }

    public ArrayList<Race> getBookmakerRaces() throws DAOException {
        ArrayList<Race> races = new ArrayList<>();
        Race race;
        connection = pool.createConnection();
        Statement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_RACE);

            resultSet = statement.executeQuery(SQL_SELECT_RACE);
            HorseDAO horseDAO = new HorseDAO();
            while (resultSet.next()) {
                int i = resultSet.getInt(1);
                ArrayList<Horse> horses = horseDAO.getBookmakerHorses(i);
                if (horses == null) {
                    continue;
                }
                race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
                race.setId(resultSet.getInt(1));
                races.add(race);
            }
            statement.close();
            return races;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        } finally {
            connection.close();
        }
    }

    public Race getRace(int id, boolean isActive) throws DAOException {
        Race race = null;
        PreparedStatement preparedStatement;
        connection = pool.createConnection();
        try {
            if (isActive)
                preparedStatement = connection.prepareStatement(SQL_SELECT_RACE_BY_ID);
            else {
                preparedStatement = connection.prepareStatement(SQL_SELECT_RESULT_BY_ID);
            }
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            HorseDAO horseDAO = new HorseDAO();
            resultSet.last();
            int i = resultSet.getInt(1);
            ArrayList<Horse> horses = horseDAO.getHorses(id);
            race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
            preparedStatement.close();
            return race;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage() + e.getSQLState(), e);
        }finally {
            if (connection != null)
                connection.close();
        }
    }

    public boolean deleteRace(int id) throws DAOException {
        boolean check = true;
        connection = pool.createConnection();
        HorseDAO dao = new HorseDAO();
        ArrayList<Horse> horses = dao.getHorses(id);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BETS);
        for (Horse horse : horses) {
            preparedStatement.setInt(1, horse.getHorseId());
            preparedStatement.executeUpdate();
        }
        preparedStatement = connection.prepareStatement(SQL_DELETE_ACTIVE_HORSES);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(SQL_DELETE_ACTIVE_RACE);
        preparedStatement.setInt(1, id);
        if (preparedStatement.executeUpdate() == 0) {
            check = false;
        }
        preparedStatement.close();
        return check;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null) {
                connection.close();
             }
        }
    }

    public int addNewRace(String card, String date) throws DAOException {
        Validator validator = new Validator();
        if (!validator.checkString(date, Validator.DATE_REGEX)) {
            return 0;
        }
        connection = pool.createConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_PREVIOUS_ID_HORSE);
        ResultSet resultSet =  preparedStatement.executeQuery();
        resultSet.next();
        int raceId = resultSet.getInt(1)+1;
        preparedStatement = connection.prepareStatement(SQL_ADD_RACE);
        preparedStatement.setInt(1, raceId);
        preparedStatement.setString(2, card);
        preparedStatement.setString(3, date);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
            if (result > 0)
                return raceId;
            else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if (connection != null)
                connection.close();
        }
    }

    public int addResults(Race race) throws DAOException {
        connection = pool.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_PREVIOUS_ID_RESULTS);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int resultId = resultSet.getInt(1) + 1;
        preparedStatement = connection.prepareStatement(SQL_ADD_RESULT_RACE);
        preparedStatement.setInt(1, resultId);
        preparedStatement.setString(2, race.getCard());
        preparedStatement.setString(3, race.getDate());
        int result = preparedStatement.executeUpdate();
        if (result > 0) {
            return resultId;
        } else {
            throw new SQLException();
        }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()+e.getSQLState(), e);
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
    }
}
