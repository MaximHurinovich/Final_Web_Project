package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RacesDAO {

    private static final String SQL_SELECT_RACE =
            "SELECT * FROM horseraces_db.race";
    private ProxyConnection connection = null;
    private Statement preparedStatement = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public RacesDAO() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Race> getRaces(){
        ArrayList<Race> races= new ArrayList<>();
        Race race = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_RACE);
            resultSet = preparedStatement.executeQuery(SQL_SELECT_RACE);
            HorsesDAO horsesDAO = new HorsesDAO();
            while(resultSet.next()) {
                int i = resultSet.getInt(1);
                ArrayList<Horse> horses = horsesDAO.getHorses(i);
                race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
                races.add(race);
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
            return races;
        }

    }
