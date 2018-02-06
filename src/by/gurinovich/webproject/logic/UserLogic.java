package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.*;
import by.gurinovich.webproject.entity.*;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class UserLogic {

    private final static Logger LOGGER = LogManager.getLogger(UserLogic.class);

    public Race getRace(int id) throws LogicalException {
        RaceDAO dao = new RaceDAO();
        Race race = null;
        try {
            race = dao.getRace(id, true);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return race;
    }

    public ArrayList<Horse> getHorses(int raceId) throws LogicalException {
        HorseDAO dao = new HorseDAO();
        ArrayList<Horse> horses;
        try {
            horses = dao.getHorses(raceId);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return horses;
    }

    public boolean addNewBet(String username, int raceId, int horseId, String betType, Double amount) throws LogicalException {
        BetDAO dao = new BetDAO();
        if (amount <= 0 || betType.isEmpty() || horseId == 0) {
            return false;
        }
        try {
            return dao.addNewBet(username, raceId, horseId, betType, amount);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean updateAccountBet(String username, double amount, Double bet) throws LogicalException {
        CardDAO dao = new CardDAO();
        try {
            if (bet <= amount && bet > 0) {
                return dao.updateAccountBet(username, amount, bet);
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return false;
    }

    private boolean checkMoney(double amount, double cardAmount) {
        return amount <= cardAmount && amount > 0;
    }

    public boolean addMoney(double amount, double currentAmount, double cardAmount, String cardNumber) throws LogicalException {
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, cardAmount) && dao.updateCardToAccount(cardNumber, cardAmount, amount, currentAmount);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean returnMoney(double amount, double currentAmount, double cardAmount, String cardNumber) throws LogicalException {
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, currentAmount) && dao.updateAccountToCard(cardNumber, cardAmount, amount, currentAmount);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public double getAccountAmount(String cardNumber) throws LogicalException {
        CardDAO dao = new CardDAO();
        double amount;
        try {
            amount = dao.getAccountAmount(cardNumber);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return amount;
    }

    public double getCardAmount(String cardNumber) throws LogicalException {
        CardDAO dao = new CardDAO();
        double amount;
        try {
            amount = dao.getCardAmount(cardNumber);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return amount;
    }

    public boolean checkData(String firstName, String secondName, String email, String cardNumber) {
        Validator validator = new Validator();
        return validator.checkString(firstName, Validator.NAME_REGEX) && validator.checkString(secondName, Validator.NAME_REGEX) &&
                validator.checkString(email, Validator.EMAIL_REGEX) && validator.checkString(cardNumber, Validator.CARD_REGEX);
    }

    public String invalidateMessage(String firstName, String secondName, String email, String cardNumber) {
        Validator validator = new Validator();
        if (!validator.checkString(firstName, Validator.NAME_REGEX) || !validator.checkString(secondName, Validator.NAME_REGEX)) {
            return MessageManager.getProperty("message.nameerror");
        } else if (!validator.checkString(email, Validator.EMAIL_REGEX)) {
            return MessageManager.getProperty("message.emailerror");
        } else if (!validator.checkString(cardNumber, Validator.CARD_REGEX)) {
            return MessageManager.getProperty("message.carderror");
        } else return MessageManager.getProperty("message.unknownerror");
    }

    public boolean updateProfile(String userName, String firstName, String secondName, String email, String cardNumber) throws LogicalException {
        EditDAO dao = new EditDAO();
        try {
            return dao.updateProfile(userName, firstName, secondName, email, cardNumber);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
    }


    public ArrayList<Odd> getActiveOdds(String username) throws LogicalException {
        BetDAO dao = new BetDAO();
        ArrayList<Odd> odds;
        try {
            odds = dao.getOdds(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd : odds) {
                if (odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }

    public ArrayList<Odd> getPassiveOdds(String username) throws LogicalException {
        BetDAO dao = new BetDAO();
        ArrayList<Odd> odds;
        try {
            odds = dao.getOdds(username);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd : odds) {
                if (!odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }

    public ArrayList<Race> results() throws LogicalException {
        ResultDAO dao = new ResultDAO();
        ArrayList<Race> results;
        try {
            results = dao.getResults();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LogicalException(e.getMessage(), e);
        }
        return results;
    }


}
