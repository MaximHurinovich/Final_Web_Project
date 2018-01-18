package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.AuthentificationDAO;

public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASS = "Qwe123";

    public static boolean checkLogin(String enterLogin, String enterPass) {
        AuthentificationDAO dao = new AuthentificationDAO();
        return dao.authenticateUser(enterLogin, enterPass);
    }
}
