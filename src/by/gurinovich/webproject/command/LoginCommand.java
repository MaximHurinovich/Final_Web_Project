package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_USERS = "usersList";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        DefaultLogic logic = new DefaultLogic();
        String page;
        String login = request.getParameter(Constant.PARAM_NAME_LOGIN);
        String pass = request.getParameter(Constant.PARAM_NAME_PASSWORD);
        if (logic.checkLogin(login, pass)) {
            request.getSession().setAttribute(Constant.ATTRIBUTE_USER_NAME, logic.userName(login, pass));
            try {
                request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST, logic.getRaces());
            } catch (LogicalException e) {
                throw new CommandException(e.getMessage());

            }
            Person user = logic.createUser(login, pass);
            if (user.isBanned()) {
                request.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN_MESSAGE, MessageManager.getProperty("message.ban"));
                router.setPage(ConfigurationManager.getProperty("path.page.login"));
                return router;
            }
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_USER, user);
            if (Constant.BOOKMAKER_ROLE.equals(user.getRole()))
                page = ConfigurationManager.getProperty("path.page.bookmaker.main");
            else if (Constant.ADMIN_ROLE.equals(user.getRole())) {
                try {
                    request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_USER_LIST, logic.getUsers());
                } catch (LogicalException e) {
                    throw new CommandException(e.getMessage());
                }
                page = ConfigurationManager.getProperty("path.page.admin.main");
            } else {
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } else {
            request.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN_MESSAGE, MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        router.setPage(page);
        return router;
    }
}