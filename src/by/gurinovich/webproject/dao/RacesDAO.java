package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.proxy.ConnectionPool;
import by.gurinovich.webproject.proxy.ProxyConnection;
import by.gurinovich.webproject.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RacesDAO {

    private static final String SQL_SELECT_USER =
            "SELECT * FROM horseraces_db.races WHERE date=?";
    private ProxyConnection connection = null;
    private PreparedStatement preparedStatement = null;
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
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, new GregorianCalendar().getTime().toString());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
            //  race = new Race(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
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
