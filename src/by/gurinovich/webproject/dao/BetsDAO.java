package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class BetsDAO {

    private static final String SQL_SELECT_BETS =
            "SELECT bookmaker,winner,top_3,outsider FROM horseraces_db.bets ORDER BY id_horse";
    private ProxyConnection connection = null;
    private Statement statement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public BetsDAO() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bet> getBets(){
        ArrayList<Bet> bets= new ArrayList<>();
        Bet bet = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_BETS);
            resultSet = statement.executeQuery(SQL_SELECT_BETS);
            while(resultSet.next()) {
                bet = new Bet(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getDouble(4));
                bets.add(bet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bets;
    }
}
