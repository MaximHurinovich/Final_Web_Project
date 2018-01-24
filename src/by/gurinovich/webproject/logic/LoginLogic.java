package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.AuthenticationDAO;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass) {
        AuthenticationDAO dao = new AuthenticationDAO();
        return dao.authenticateUser(enterLogin, enterPass);
    }
}
