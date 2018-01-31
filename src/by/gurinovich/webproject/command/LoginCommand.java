package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.AuthenticationDAO;
import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.LoginLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.checkLogin(login, pass)) {
            AuthenticationDAO dao = new AuthenticationDAO();
            RacesDAO racesDAO = new RacesDAO();
            request.getSession().setAttribute("user", dao.userName(login, pass));
            request.getSession().setAttribute("racesList", racesDAO.getRaces());
            request.getSession().setAttribute("userfull", dao.createUser(login, pass));
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}