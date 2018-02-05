package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetDAO;
import by.gurinovich.webproject.dao.RaceDAO;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.exception.LogicalException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookmakerLogic {

    public ArrayList<Race> getRaces() throws LogicalException {
        RaceDAO raceDAO = new RaceDAO();
        try {
            return raceDAO.getBookmakerRaces();
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage());
        }
    }

    public boolean setBets(Race race, String[] wins, String[] top3s, String[] outsiders, int bookmakerID) throws LogicalException {
        Double[] winBets = new Double[race.getHorses().size()];
        Double[] top3Bets = new Double[race.getHorses().size()];
        Double[] outsiderBets = new Double[race.getHorses().size()];
        BetDAO dao = new BetDAO();
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
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage());
        }
    }
}
