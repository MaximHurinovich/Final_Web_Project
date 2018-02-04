package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class BetCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        int raceId = Integer.valueOf(request.getParameter(Constant.ATTRIBUTE_NAME_RACE_ID));
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RACE_ID, raceId);
        try {
            request.getSession().setAttribute(Constant.ATTRIBUTE_BET_RACE, userLogic.getRace(raceId));
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        router.setPage(ConfigurationManager.getProperty("path.page.bet"));
        return router;
    }
}
