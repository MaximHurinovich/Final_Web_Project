package by.gurinovich.webproject.dao;

import by.gurinovich.webproject.entity.Bet;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.pool.ConnectionPool;
import by.gurinovich.webproject.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class HorsesDAO {
    private static final String SQL_SELECT_HORSES =
            "SELECT * FROM horseraces_db.horses WHERE race_number=?";
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
}
