package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class BetsDAO {

    private static final String SQL_SELECT_BETS =
            "SELECT * FROM horseraces_db.bets ORDER BY id_horse";
    private static final String SQL_SELECT_BET_BY_HORSE =
            "SELECT * FROM horseraces_db.bets WHERE id_horse=?";
    private static final String SQL_SELECT_USER_ODDS_BY_USERNAME =
            "SELECT * FROM horseraces_db.user_odds WHERE username=?";
    private static final String SQL_SELECT_USER_ODDS =
            "SELECT * FROM horseraces_db.user_odds WHERE is_active='true'";
    private static final String SQL_SELECT_HORSE_ACTIVE =
            "SELECT name FROM horseraces_db.horses WHERE id_horse=?";
    private static final String SQL_SELECT_HORSE_NONACTIVE =
            "SELECT name FROM horseraces_db.history_horses WHERE id_horse=?";
    private static final String SQL_ADD_BET =
            "INSERT INTO horseraces_db.user_odds values(NULL,?,?,?,?,ROUND(?,2),'true', NULL)";
    private static final String SQL_SELECT_USER_ODDS_BY_RACE =
            "SELECT * FROM horseraces_db.user_odds WHERE race_id=? AND is_active='true'";
    private static final String SQL_UPDATE_USER_ODDS =
            "UPDATE horseraces_db.user_odds SET is_active='false',success=?,race_id=? WHERE race_id=? AND horse_id=? AND type=?";
    private static final String TRUE = "true";
    private ProxyConnection connection = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    ArrayList<Bet> getBets() throws SQLException {
        ArrayList<Bet> bets= new ArrayList<>();
        Bet bet;
            connection = pool.getConnection();
        Statement statement = connection.prepareStatement(SQL_SELECT_BETS);
            resultSet = statement.executeQuery(SQL_SELECT_BETS);
            while(resultSet.next()) {
                bet = new Bet(resultSet.getInt(1), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(2));
                bets.add(bet);
            }
        statement.close();

        if(connection!=null)
                connection.close();


        return bets;
    }

    Bet getBet(int horseId) throws SQLException {
        connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BET_BY_HORSE);
        preparedStatement.setInt(1, horseId);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Bet bet = new Bet(resultSet.getInt(1), resultSet.getDouble(3),
                resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(2));
        preparedStatement.close();
        connection.close();
        return bet;
    }

    public ArrayList<Odd> getOdds(String username) throws SQLException {
        ArrayList<Odd> odds= new ArrayList<>();
        Odd odd;
        RacesDAO dao = new RacesDAO();
        PreparedStatement preparedStatement;
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_SELECT_USER_ODDS_BY_USERNAME);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            if(TRUE.equals(resultSet.getString(7))) {
                preparedStatement = connection.prepareStatement(SQL_SELECT_HORSE_ACTIVE);
                preparedStatement.setString(1, String.valueOf(resultSet.getInt(4)));
                ResultSet horse = preparedStatement.executeQuery();
                horse.next();
                odd = new Odd(resultSet.getString(2), dao.getRace(resultSet.getInt(3), true).getDate(), horse.getString(1),
                        resultSet.getString(5), resultSet.getDouble(6), true);
                odds.add(odd);
            }else{
                preparedStatement = connection.prepareStatement(SQL_SELECT_HORSE_NONACTIVE);
                preparedStatement.setString(1, String.valueOf(resultSet.getInt(4)));
                ResultSet horse = preparedStatement.executeQuery();
                horse.next();
                odd = new Odd(resultSet.getString(2), dao.getRace(resultSet.getInt(3), false).getDate(), horse.getString(1),
                        resultSet.getString(5), resultSet.getDouble(6), false);
                if(TRUE.equals(resultSet.getString(8))) {
                    odd.setSuccess(true);
                }
                odd.setHorseId(resultSet.getInt(4));
                preparedStatement.close();
                odds.add(odd);
            }
        }

            if(connection!=null)
                connection.close();


        return odds;
    }

    public ArrayList<Odd> getOdds(int raceId) throws SQLException {
        ArrayList<Odd> odds= new ArrayList<>();
        Odd odd;
        RacesDAO dao = new RacesDAO();
        PreparedStatement preparedStatement;
        connection = pool.getConnection();
        preparedStatement = connection.prepareStatement(SQL_SELECT_USER_ODDS_BY_RACE);
        preparedStatement.setInt(1, raceId);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
                preparedStatement = connection.prepareStatement(SQL_SELECT_HORSE_ACTIVE);
                preparedStatement.setString(1, String.valueOf(resultSet.getInt(4)));
                ResultSet horse = preparedStatement.executeQuery();
                horse.next();
                String date = dao.getRace(resultSet.getInt(3), true).getDate();
                String name =horse.getString(1);
                String typeOfOdd = resultSet.getString(5);
                double amount = resultSet.getDouble(6);
                String username = resultSet.getString(2);
                odd = new Odd(username, date, name, typeOfOdd, amount, true);
                odd.setOdd(amount);
                odd.setHorseId(resultSet.getInt(4));
                odds.add(odd);
            }
                preparedStatement.close();

        if(connection!=null)
            connection.close();


        return odds;
    }

    public ArrayList<Odd> getOdds() throws SQLException {
        ArrayList<Odd> odds= new ArrayList<>();
        Odd odd;
        RacesDAO dao = new RacesDAO();
        PreparedStatement preparedStatement;
        connection = pool.getConnection();
        Statement statement = connection.prepareStatement(SQL_SELECT_USER_ODDS);
        resultSet = statement.executeQuery(SQL_SELECT_USER_ODDS);
        while(resultSet.next()) {
            preparedStatement = connection.prepareStatement(SQL_SELECT_HORSE_ACTIVE);
            preparedStatement.setString(1, String.valueOf(resultSet.getInt(4)));
            ResultSet horse = preparedStatement.executeQuery();
            horse.next();
            String date = dao.getRace(resultSet.getInt(3), true).getDate();
            String name =horse.getString(1);
            String typeOfOdd = resultSet.getString(5);
            double amount = resultSet.getDouble(6);
            String username = resultSet.getString(2);
            odd = new Odd(username, date, name, typeOfOdd, amount, true);
            odd.setHorseId(resultSet.getInt(4));
            odds.add(odd);
        }
        statement.close();

        if(connection!=null)
            connection.close();


        return odds;
    }

    public boolean addNewBet(String username, int raceId, int horseId, String betType, double amount) throws SQLException{
        connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_BET);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, raceId);
        preparedStatement.setInt(3, horseId);
        preparedStatement.setString(4, betType);
        preparedStatement.setDouble(5, amount);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        if(connection!=null)
            connection.close();
        return result>0;
    }

    public boolean updateOdds(ArrayList<Odd> odds, int raceID, int resultID) throws SQLException {
        connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_ODDS);
        int result = 0;
        for(Odd odd: odds) {
            preparedStatement.setString(1, odd.isSuccess() ? "true" : "false");
            preparedStatement.setInt(2,resultID);
            preparedStatement.setInt(3, raceID);
            preparedStatement.setInt(4, odd.getHorseId());
            preparedStatement.setString(5, odd.getType());
            result = preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
        return result>0;
    }
}
