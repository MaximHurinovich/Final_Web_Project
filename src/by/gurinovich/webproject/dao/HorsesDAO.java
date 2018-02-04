package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.id.IDGenerator;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class HorsesDAO {
    private static final String SQL_SELECT_HORSES =
            "SELECT * FROM horseraces_db.horses WHERE race_number=?";
    private static final String SQL_ADD_HORSES =
            "INSERT INTO horseraces_db.horses VALUES(?,?,?)";
    private static final String SQL_ADD_RESULT_HORSES =
            "INSERT INTO horseraces_db.history_horses VALUES(?,?,?,?)";
    private ProxyConnection connection = null;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ArrayList<Horse> getHorses(int race) throws SQLException {
        ArrayList<Horse> horses= new ArrayList<>();
        Horse horse;
        connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_HORSES);
        statement.setString(1, String.valueOf(race));
        ResultSet resultSet = statement.executeQuery();
        BetsDAO  betsDAO = new BetsDAO();
        ArrayList<Bet> bets = betsDAO.getBets();
        int i = 0;
        while(resultSet.next()) {
            Bet temp = null;
            for(Bet bet: bets){
                if (bet.getHorseID()==resultSet.getInt(1)){
                    temp = bet;
                    break;
                }
            }
            if(temp==null){
                return null;
            }
            horse = new Horse(resultSet.getInt(2), resultSet.getString(3), temp);
            horse.setHorseId(resultSet.getInt(1));
            horses.add(horse);
            i++;
        }
        statement.close();
        if(connection!=null)
            connection.close();

        return horses;
    }

    public ArrayList<Horse> getBookmakerHorses(int race) throws SQLException {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horse;
        connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_HORSES);
        statement.setString(1, String.valueOf(race));
        ResultSet resultSet = statement.executeQuery();
        BetsDAO  betsDAO = new BetsDAO();
        ArrayList<Bet> bets = betsDAO.getBets();
        int i = 0;
        while(resultSet.next()) {
            Bet temp = null;
            for(Bet bet: bets){
                if (bet.getHorseID()==resultSet.getInt(1)){
                    temp = bet;
                    break;
                }
            }
            if(temp==null){
                horse = new Horse(resultSet.getInt(2), resultSet.getString(3), temp);
                horse.setHorseId(resultSet.getInt(1));
                horses.add(horse);
                i++;
            }else{
                return null;
            }
        }
        statement.close();
        if(connection!=null)
            connection.close();

        return horses;
    }

    public boolean addHorses(int raceId, HashSet<String> names) throws SQLException {
        connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_HORSES);
        preparedStatement.setInt(2, raceId);
        int result = 0;
        for(String name: names){
            preparedStatement.setInt(1, IDGenerator.generateID());
            preparedStatement.setString(3, name);
            result = preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
        return result>0;
    }

    public boolean addResultHorses(ArrayList<Horse> horses, int resultId) throws SQLException {
        connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_RESULT_HORSES);
        preparedStatement.setInt(3, resultId);
        int result = 0;
        for(Horse horse: horses){
            preparedStatement.setInt(1, horse.getHorseId());
            preparedStatement.setString(2, horse.getName());
            preparedStatement.setInt(4, horse.getPlace());
            result = preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
        return result>0;
    }
}
