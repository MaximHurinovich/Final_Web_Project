package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class MyBetsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String username = ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).getUsername();
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_ACTIVE_ODDS, userLogic.getActiveOdds(username));
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_NONACTIVE_ODDS, userLogic.getPassiveOdds(username));
        router.setPage(ConfigurationManager.getProperty("path.page.mybets"));
        return router;
    }
}
