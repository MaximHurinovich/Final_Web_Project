package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_USERS = "usersList";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        DefaultLogic logic = new DefaultLogic();
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (logic.checkLogin(login, pass)) {
            request.getSession().setAttribute("user", logic.userName(login, pass));
            request.getSession().setAttribute("racesList", logic.getRaces());
            Person user = logic.createUser(login, pass);
            if(user.isBanned()){
                request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.ban"));
                router.setPage(ConfigurationManager.getProperty("path.page.login"));
                return router;
            }
            request.getSession().setAttribute("userfull", user);
            if("u".equals(user.getRole()))
                page = ConfigurationManager.getProperty("path.page.main");
            else{
                request.getSession().setAttribute(PARAM_NAME_USERS, logic.getUsers());
                page = ConfigurationManager.getProperty("path.page.admin.main");
            }
        } else {
            request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        router.setPage(page);
        return router;
    }
}