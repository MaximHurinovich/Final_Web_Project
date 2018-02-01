package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetsDAO;
import by.gurinovich.webproject.dao.CardDAO;
import by.gurinovich.webproject.dao.HorsesDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddBetLogic {
    public static Race getRace(int id){
        RacesDAO dao = new RacesDAO();
        Race race = null;
        try {
            race = dao.getRace(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return race;
    }

    public static ArrayList<Horse> getHorses(int raceId){
        HorsesDAO dao = new HorsesDAO();
        ArrayList<Horse> horses = null;
        try {
            horses = dao.getHorses(raceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horses;
    }

    public static boolean addNewBet(String username, int raceId, int horseId, String betType, double amount){
        BetsDAO dao = new BetsDAO();
        try {
            return dao.addNewBet(username, raceId, horseId, betType, amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccountBet(String username, double amount, double bet){
        CardDAO dao = new CardDAO();
        try {
            if(bet<=amount) {
                return dao.updateAccoundBet(username, amount, bet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
