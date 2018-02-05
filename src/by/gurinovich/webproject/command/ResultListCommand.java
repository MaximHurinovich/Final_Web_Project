package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class ResultListCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        try {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RESULTS_LIST, userLogic.results());
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());
        }
        router.setPage(ConfigurationManager.getProperty("path.page.results"));
        return router;
    }
}
