package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.*;
import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserLogic {

    public  Race getRace(int id){
        RacesDAO dao = new RacesDAO();
        Race race = null;
        try {
            race = dao.getRace(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return race;
    }

    public  ArrayList<Horse> getHorses(int raceId){
        HorsesDAO dao = new HorsesDAO();
        ArrayList<Horse> horses = null;
        try {
            horses = dao.getHorses(raceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horses;
    }

    public boolean addNewBet(String username, int raceId, int horseId, String betType, double amount){
        BetsDAO dao = new BetsDAO();
        try {
            return dao.addNewBet(username, raceId, horseId, betType, amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  boolean updateAccountBet(String username, double amount, double bet){
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

    private  boolean checkMoney(double amount, double cardAmount){
        return amount <= cardAmount;
    }

    public boolean addMoney(double amount, double currentAmount, double cardAmount, String cardNumber){
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, cardAmount)&& dao.updateCardToAccount(cardNumber, cardAmount, amount, currentAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnMoney(double amount, double currentAmount, double cardAmount, String cardNumber){
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, currentAmount)&& dao.updateAccountToCard(cardNumber, cardAmount, amount, currentAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public double getAccountAmount(String cardNumber){
        CardDAO dao = new CardDAO();
        double amount = 0;
        try {
            amount= dao.getAccountAmount(cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public double getCardAmount(String cardNumber){
        CardDAO dao = new CardDAO();
        double amount = 0;
        try {
            amount = dao.getCardAmount(cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public boolean checkData(String firstName, String secondName, String email, String cardNumber){
        Validator validator = new Validator();
        return validator.checkString(firstName, Validator.NAME_REGEX)&& validator.checkString(secondName, Validator.NAME_REGEX) &&
                validator.checkString(email, Validator.EMAIL_REGEX) && validator.checkString(cardNumber, Validator.CARD_REGEX);
    }

    public String invalidateMessage(String firstName, String secondName, String email, String cardNumber){
        Validator validator = new Validator();
        if(!validator.checkString(firstName, Validator.NAME_REGEX)||!validator.checkString(secondName, Validator.NAME_REGEX)){
            return MessageManager.getProperty("message.nameerror");
        }
        else if(!validator.checkString(email, Validator.EMAIL_REGEX)){
            return MessageManager.getProperty("message.emailerror");
        }
        else if(!validator.checkString(cardNumber, Validator.CARD_REGEX)){
            return MessageManager.getProperty("message.carderror");
        }
        else return MessageManager.getProperty("message.unknownerror");
    }

    public boolean updateProfile(String userName, String firstName, String secondName, String email, String cardNumber){
        EditDAO dao = new EditDAO();
        try {
            return dao.updateProfile(userName, firstName, secondName, email, cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogin(String enterLogin, String enterPass) {
        AuthenticationDAO dao = new AuthenticationDAO();
        try {
            return dao.authenticateUser(enterLogin, enterPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String userName(String login, String password){
        AuthenticationDAO dao = new AuthenticationDAO();
        String username = null;
        try {
            username = dao.userName(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public  ArrayList<Race> getRaces(){
        RacesDAO dao = new RacesDAO();
        ArrayList<Race> races = null;
        try {
            races =  dao.getRaces();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return races;
    }

    public  User createUser(String login, String password){
        AuthenticationDAO dao = new AuthenticationDAO();
        User user = null;
        try {
            user = dao.createUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public  ArrayList<Odd> getActiveOdds(String username){
        BetsDAO dao = new BetsDAO();
        ArrayList<Odd> odds = null;
        try {
            odds = dao.getOdds(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd: odds) {
                if(odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }

    public ArrayList<Odd> getPassiveOdds(String username){
        BetsDAO dao = new BetsDAO();
        ArrayList<Odd> odds = null;
        try {
            odds = dao.getOdds(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd: odds) {
                if(!odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }

    public ArrayList<Race> results(){
        ResultsDAO dao = new ResultsDAO();
        ArrayList<Race> results = null;
        try {
            results= dao.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    public boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        RegistrationDAO dao = new RegistrationDAO();
        try {
            if(dao.checkCard(cardNumber, cardPassword) && dao.checkUserName(userName)) {
                return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String invalidateMessage(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        Validator validator = new Validator();
        if(!validator.checkString(firstName, Validator.NAME_REGEX)||!validator.checkString(secondName, Validator.NAME_REGEX)){
            return MessageManager.getProperty("message.nameerror");
        }
        else if(!validator.checkString(userName, Validator.USERNAME_REGEX)){
            return MessageManager.getProperty("message.usernameerror");
        }
        else if(!validator.checkString(password, Validator.PASSWORD_REGEX)){
            return MessageManager.getProperty("message.passworderror");
        }
        else if(!validator.checkString(email, Validator.EMAIL_REGEX)){
            return MessageManager.getProperty("message.emailerror");
        }
        else if(!validator.checkString(cardNumber, Validator.CARD_REGEX)|| !validator.checkString(cardPassword, Validator.CARD_REGEX)){
            return MessageManager.getProperty("message.carderror");
        }
        else return MessageManager.getProperty("message.unknownerror");
    }
}
