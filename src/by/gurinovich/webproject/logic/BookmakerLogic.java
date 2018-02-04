package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetsDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookmakerLogic {

    public ArrayList<Race> getRaces(){
        RacesDAO racesDAO = new RacesDAO();
        try {
            return racesDAO.getBookmakerRaces();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setBets(Race race, String[] wins, String[] top3s, String[] outsiders, int bookmakerID){
        Double[] winBets = new Double[race.getHorses().size()];
        Double[] top3Bets = new Double[race.getHorses().size()];
        Double[] outsiderBets = new Double[race.getHorses().size()];
        BetsDAO dao = new BetsDAO();
        for (int i = 0; i < winBets.length; i++){
            winBets[i] = Double.valueOf(wins[i]);
            top3Bets[i] = Double.valueOf(top3s[i]);
            outsiderBets[i] = Double.valueOf(outsiders[i]);
        }
        ArrayList<Horse> horses = race.getHorses();
        ArrayList<Integer> horsesID = new ArrayList<>();
        for(Horse horse: horses){
            horsesID.add(horse.getHorseId());
        }
        try {
            return dao.setBets(horsesID, winBets, top3Bets, outsiderBets, bookmakerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
