package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.AuthentificationDAO;
import by.gurinovich.webproject.logic.LoginLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.checkLogin(login, pass)) {
            AuthentificationDAO dao = new AuthentificationDAO();
            request.setAttribute("user", dao.userName(login, pass));
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}