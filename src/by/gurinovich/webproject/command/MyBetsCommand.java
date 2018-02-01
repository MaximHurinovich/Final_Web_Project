package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.OddLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class MyBetsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String username = ((User)request.getSession().getAttribute("userfull")).getUsername();
        request.getSession().setAttribute("activeOdds", OddLogic.getActiveOdds(username));
        request.getSession().setAttribute("nonactiveOdds", OddLogic.getPassiveOdds(username));
        router.setPage(ConfigurationManager.getProperty("path.page.mybets"));
        return router;
    }
}
