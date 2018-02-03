package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.UsersDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.dao.RegistrationDAO;
import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

public class DefaultLogic {
    public boolean checkLogin(String enterLogin, String enterPass) {
        UsersDAO dao = new UsersDAO();
        try {
            return dao.authenticateUser(enterLogin, enterPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String userName(String login, String password) {
        UsersDAO dao = new UsersDAO();
        String username = null;
        try {
            username = dao.userName(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public Person createUser(String login, String password) {
        UsersDAO dao = new UsersDAO();
        Person user = null;
        try {
            user = dao.createUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword) {
        RegistrationDAO dao = new RegistrationDAO();
        try {
            if (dao.checkCard(cardNumber, cardPassword) && dao.checkUserName(userName)) {
                return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Race> getRaces() {
        RacesDAO dao = new RacesDAO();
        ArrayList<Race> races = null;
        try {
            races = dao.getRaces();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return races;
    }

    public ArrayList<Person> getUsers() {
        UsersDAO dao = new UsersDAO();
        ArrayList<Person> users = new ArrayList<>();
        try {
            users = dao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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
