package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class HorsesDAO {
    private static final String SQL_SELECT_HORSES =
            "SELECT * FROM horseraces_db.horses WHERE race_number=?";
    private ProxyConnection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public HorsesDAO() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Horse> getHorses(int race){
        ArrayList<Horse> horses= new ArrayList<>();
        Horse horse = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_HORSES);
            statement.setString(1, String.valueOf(race));
            resultSet = statement.executeQuery();
            BetsDAO  betsDAO = new BetsDAO();
            ArrayList<Bet> bets = betsDAO.getBets();
            int i = 0;
            while(resultSet.next()) {
                Bet bet = bets.get(i);
                horse = new Horse(resultSet.getInt(2), resultSet.getString(3), bet);
                horses.add(horse);
                i++;
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
        return horses;
    }
}
