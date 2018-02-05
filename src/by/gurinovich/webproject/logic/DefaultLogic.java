package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.UserDAO;
import by.gurinovich.webproject.dao.RaceDAO;
import by.gurinovich.webproject.dao.RegistrationDAO;
import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.DAOException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

public class DefaultLogic {
    public boolean checkLogin(String enterLogin, String enterPass) throws LogicalException {
        UserDAO dao = new UserDAO();
        try {
            return dao.authenticateUser(enterLogin, enterPass);
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public String userName(String login, String password) throws LogicalException {
        UserDAO dao = new UserDAO();
        String username;
        try {
            username = dao.userName(login, password);
            return username;
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public Person createUser(String login, String password) throws LogicalException {
        UserDAO dao = new UserDAO();
        Person user;
        try {
            user = dao.createUser(login, password);
            return user;
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword) throws LogicalException {
        RegistrationDAO dao = new RegistrationDAO();
        try {
            if (dao.checkCard(cardNumber, cardPassword) && dao.checkUserName(userName)) {
                return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
            }
        } catch (DAOException e){
            throw new LogicalException(e.getMessage(), e);
        }
        return false;
    }

    public ArrayList<Race> getRaces() throws LogicalException {
        RaceDAO dao = new RaceDAO();
        ArrayList<Race> races;
        try {
            races = dao.getRaces();
            return races;
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public ArrayList<Person> getUsers() throws LogicalException {
        UserDAO dao = new UserDAO();
        ArrayList<Person> users;
        try {
            users = dao.getUsers();
            return users;
        } catch (DAOException e) {
            throw new LogicalException(e.getMessage(), e);
        }
    }

    public String invalidateMessage(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword) {
        Validator validator = new Validator();
        if (!validator.checkString(firstName, Validator.NAME_REGEX) || !validator.checkString(secondName, Validator.NAME_REGEX)) {
            return MessageManager.getProperty("message.nameerror");
        } else if (!validator.checkString(userName, Validator.USERNAME_REGEX)) {
            return MessageManager.getProperty("message.usernameerror");
        } else if (!validator.checkString(password, Validator.PASSWORD_REGEX)) {
            return MessageManager.getProperty("message.passworderror");
        } else if (!validator.checkString(email, Validator.EMAIL_REGEX)) {
            return MessageManager.getProperty("message.emailerror");
        } else if (!validator.checkString(cardNumber, Validator.CARD_REGEX) || !validator.checkString(cardPassword, Validator.CARD_REGEX)) {
            return MessageManager.getProperty("message.carderror");
        } else return MessageManager.getProperty("message.unknownerror");
    }
}
