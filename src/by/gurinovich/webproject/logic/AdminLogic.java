package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetDAO;
import by.gurinovich.webproject.dao.HorseDAO;
import by.gurinovich.webproject.dao.RaceDAO;
import by.gurinovich.webproject.dao.UserDAO;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class AdminLogic {
    private final static Logger LOGGER = LogManager.getLogger(AdminLogic.class);
    public boolean deleteRace(int id) throws LogicalException {
        RaceDAO dao = new RaceDAO();
        try {
            return dao.deleteRace(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean banUser(String username) throws LogicalException {
        UserDAO dao = new UserDAO();
        try {
            return dao.banUser(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean makeAdmin(String username) throws LogicalException {
        UserDAO dao = new UserDAO();
        try {
            return dao.makeAdmin(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean makeBookmaker(String username) throws LogicalException {
        UserDAO dao = new UserDAO();
        try {
            return dao.makeBookmaker(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean addNewRace(String card, String date, HashSet<String> horses) throws LogicalException {
        RaceDAO raceDAO = new RaceDAO();
        HorseDAO horseDAO = new HorseDAO();
        int raceID;
        try {
            raceID = raceDAO.addNewRace(card, date);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);

        }
        if (raceID == 0) {
            return false;
        }
        try {
            return horseDAO.addHorses(raceID, horses);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);

        }
    }

    public boolean runRace(int raceId) throws LogicalException {
        BetDAO betDAO = new BetDAO();
        HorseDAO horseDAO = new HorseDAO();
        UserDAO userDAO = new UserDAO();
        RaceDAO raceDAO = new RaceDAO();
        ArrayList<Odd> raceOdds;
        try {
            raceOdds = betDAO.getOdds(raceId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        ArrayList<Horse> horses;
        try {
            horses = horseDAO.getHorses(raceId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        ArrayList<Integer> places = new ArrayList<>();
        for (int i = 0; i < horses.size(); i++) {
            places.add(i + 1);
        }
        Collections.shuffle(places);
        Collections.shuffle(places);
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).setPlace(places.get(i));
        }
        for (Odd odd : raceOdds) {
            odd.setActive(false);
            for (Horse horse : horses) {
                if (odd.getHorseName().equals(horse.getName())) {
                    if (Constant.SQL_WINNER_BET.equals(odd.getType()) && horse.getPlace() == 1) {
                        odd.setSuccess(true);
                    } else if (Constant.SQL_TOP3_BET.equals(odd.getType()) && horse.getPlace() >= 1 && horse.getPlace() <= 3) {
                        odd.setSuccess(true);
                    } else if (Constant.SQL_OUTSIDER_BET.equals(odd.getType()) && horse.getPlace() == horses.size()) {
                        odd.setSuccess(true);
                    }
                }
            }
            if (odd.isSuccess()) {
                try {
                    userDAO.winningBet(odd.getUsername(), odd.getOdd(), odd.getHorseId(), odd.getType());
                } catch (DAOException e) {
                    LOGGER.error(e.getMessage(), e);
                    throw new LogicalException(e.getMessage(), e);
                }
            }
        }
        Race race;
        int resultId;
        try {
            race = raceDAO.getRace(raceId, true);
            resultId = raceDAO.addResults(race);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        try {
            horseDAO.addResultHorses(horses, resultId);
            betDAO.updateOdds(raceOdds, raceId, resultId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        try {
            return raceDAO.deleteRace(raceId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public ArrayList<Odd> getOdds() throws LogicalException {
        BetDAO dao = new BetDAO();
        ArrayList<Odd> odds;
        try {
            odds = dao.getOdds();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return odds;
    }
}
