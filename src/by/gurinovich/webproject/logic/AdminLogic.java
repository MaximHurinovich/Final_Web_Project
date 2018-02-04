package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetsDAO;
import by.gurinovich.webproject.dao.HorsesDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.dao.UsersDAO;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.util.Constant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class AdminLogic {

    public boolean deleteRace(int id) throws LogicalException {
        RacesDAO dao = new RacesDAO();
        try {
            return dao.deleteRace(id);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
    }

    public boolean banUser(String username) throws LogicalException {
        UsersDAO dao = new UsersDAO();
        try {
            return dao.banUser(username);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
    }

    public boolean makeAdmin(String username) throws LogicalException {
        UsersDAO dao = new UsersDAO();
        try {
            return dao.makeAdmin(username);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
    }

    public boolean makeBookmaker(String username) throws LogicalException {
        UsersDAO dao = new UsersDAO();
        try {
            return dao.makeBookmaker(username);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
    }

    public boolean addNewRace(String card, String date, HashSet<String> horses) throws LogicalException {
        RacesDAO racesDAO = new RacesDAO();
        HorsesDAO horsesDAO = new HorsesDAO();
        int raceID = 0;
        try {
            raceID = racesDAO.addNewRace(card, date);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());

        }
        if (raceID == 0) {
            return false;
        }
        try {
            return horsesDAO.addHorses(raceID, horses);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());

        }
    }

    public boolean runRace(int raceId) throws LogicalException {
        BetsDAO betsDAO = new BetsDAO();
        HorsesDAO horsesDAO = new HorsesDAO();
        UsersDAO usersDAO = new UsersDAO();
        RacesDAO racesDAO = new RacesDAO();
        ArrayList<Odd> raceOdds = new ArrayList<>();
        try {
            raceOdds = betsDAO.getOdds(raceId);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
        ArrayList<Horse> horses;
        try {
            horses = horsesDAO.getHorses(raceId);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
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
                    } else if (Constant.SQL_OUTSIDER_BET.equals(odd.getType()) && horse.getPlace() == horses.size() - 1) {
                        odd.setSuccess(true);
                    }
                }
            }
            if (odd.isSuccess()) {
                try {
                    usersDAO.winningBet(odd.getUsername(), odd.getOdd(), odd.getHorseId(), odd.getType());
                } catch (SQLException e) {
                    throw new LogicalException(e.getMessage() + e.getSQLState());
                }
            }
        }
        Race race;
        int resultId;
        try {
            race = racesDAO.getRace(raceId, true);
            resultId = racesDAO.addResults(race);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
        try {
            horsesDAO.addResultHorses(horses, resultId);
            betsDAO.updateOdds(raceOdds, raceId, resultId);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
        try {
            return racesDAO.deleteRace(raceId);
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
    }

    public ArrayList<Odd> getOdds() throws LogicalException {
        BetsDAO dao = new BetsDAO();
        ArrayList<Odd> odds;
        try {
            odds = dao.getOdds();
        } catch (SQLException e) {
            throw new LogicalException(e.getMessage() + e.getSQLState());
        }
        return odds;
    }
}
