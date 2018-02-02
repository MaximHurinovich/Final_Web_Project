package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class RacesDAO {

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
    private ProxyConnection connection = null;
    private ResultSet resultSet;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ArrayList<Race> getRaces() throws SQLException {
        ArrayList<Race> races= new ArrayList<>();
        Race race;
            connection = pool.getConnection();
            Statement statement = connection.prepareStatement(SQL_SELECT_RACE);
            resultSet = statement.executeQuery(SQL_SELECT_RACE);
            HorsesDAO horsesDAO = new HorsesDAO();
            while(resultSet.next()) {
                int i = resultSet.getInt(1);
                ArrayList<Horse> horses = horsesDAO.getHorses(i);
                race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
                race.setId(resultSet.getInt(1));
                races.add(race);
            }
        statement.close();
        connection.close();


            return races;
        }

        public Race getRace(int id, boolean isActive) throws SQLException {
            Race race = null;
            PreparedStatement preparedStatement;
                connection = pool.getConnection();
                if(isActive)
                    preparedStatement = connection.prepareStatement(SQL_SELECT_RACE_BY_ID);
                else{
                    preparedStatement = connection.prepareStatement(SQL_SELECT_RESULT_BY_ID);
                }
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                HorsesDAO horsesDAO = new HorsesDAO();
                resultSet.last();
                int i = resultSet.getInt(1);
                ArrayList<Horse> horses = horsesDAO.getHorses(id);
                race = new Race(resultSet.getString(2), resultSet.getString(3), horses);
            preparedStatement.close();
            if(connection!=null)
                    connection.close();

            return race;
        }

        public boolean deleteRace(int id) throws SQLException{
            boolean check = true;
            connection = pool.getConnection();
            HorsesDAO dao = new HorsesDAO();
            ArrayList<Horse> horses = dao.getHorses(id);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BETS);
            for(Horse horse: horses){
                preparedStatement.setInt(1, horse.getHorseId());
                preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_ACTIVE_HORSES);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ACTIVE_RACE);
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate()==0){
                check = false;
            }
            preparedStatement.close();
            if(connection!=null){
                connection.close();
            }
            return check;
        }

    }
