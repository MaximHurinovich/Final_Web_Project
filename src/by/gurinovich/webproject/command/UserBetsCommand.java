package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class UserBetsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String username = ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).getUsername();
        try {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_ACTIVE_ODDS, userLogic.getActiveOdds(username));
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());
        }
        try {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_NONACTIVE_ODDS, userLogic.getPassiveOdds(username));
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage(), e);
        }
        router.setPage(ConfigurationManager.getProperty("path.page.mybets"));
        return router;
    }
}
