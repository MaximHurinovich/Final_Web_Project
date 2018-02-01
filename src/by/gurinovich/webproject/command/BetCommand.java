package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class BetCommand implements ActionCommand {
    private static final String PARAM_RACE_ID = "race_id";
    private static final String ATTRIBUTE_BET_RACE = "betrace";
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        int raceId = Integer.valueOf(request.getParameter(PARAM_RACE_ID));
        request.getSession().setAttribute(PARAM_RACE_ID, raceId);
        request.getSession().setAttribute(ATTRIBUTE_BET_RACE, userLogic.getRace(raceId));
        router.setPage(ConfigurationManager.getProperty("path.page.bet"));
        return router;
    }
}
