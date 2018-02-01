package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.AuthenticationDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass) {
        AuthenticationDAO dao = new AuthenticationDAO();
        try {
            return dao.authenticateUser(enterLogin, enterPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String userName(String login, String password){
        AuthenticationDAO dao = new AuthenticationDAO();
        String username = null;
        try {
            username = dao.userName(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public static ArrayList<Race> getRaces(){
        RacesDAO dao = new RacesDAO();
        ArrayList<Race> races = null;
        try {
            races =  dao.getRaces();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return races;
    }

    public static User createUser(String login, String password){
        AuthenticationDAO dao = new AuthenticationDAO();
        User user = null;
        try {
            user = dao.createUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
