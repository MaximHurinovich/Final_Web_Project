package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultsDAO {
    private static final String SQL_SELECT_RESULT_RACES =
            "SELECT * FROM horseraces_db.history_race";
    private static final String SQL_SELECT_RESULT_HORSES =
            "SELECT * FROM horseraces_db.history_horses WHERE id_race=? ORDER BY place";
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ArrayList<Race> getResults() throws SQLException {
        ArrayList<Race> races = new ArrayList<>();
        Race race;
        Horse horse;
        ProxyConnection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RESULT_RACES);
        ResultSet raceSet = preparedStatement.executeQuery(SQL_SELECT_RESULT_RACES);
        while (raceSet.next()) {
            int i = raceSet.getInt(1);
            preparedStatement = connection.prepareStatement(SQL_SELECT_RESULT_HORSES);
            preparedStatement.setInt(1, i);
            ResultSet horseSet = preparedStatement.executeQuery();
            ArrayList<Horse> horses = new ArrayList<>();
            while (horseSet.next()) {
                horse = new Horse(horseSet.getInt(3), horseSet.getString(2), horseSet.getInt(4));
                horses.add(horse);
            }
            ArrayList<Horse> temp = new ArrayList<>(horses);
            race = new Race(raceSet.getString(2), raceSet.getString(3), temp);
            races.add(race);
            horses.clear();
        }
        preparedStatement.close();
        connection.close();


        return races;
    }

}
