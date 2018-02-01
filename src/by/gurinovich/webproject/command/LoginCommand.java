package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (userLogic.checkLogin(login, pass)) {

            request.getSession().setAttribute("user", userLogic.userName(login, pass));
            request.getSession().setAttribute("racesList", userLogic.getRaces());
            request.getSession().setAttribute("userfull", userLogic.createUser(login, pass));
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        router.setPage(page);
        return router;
    }
}