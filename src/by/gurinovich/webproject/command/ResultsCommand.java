package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class ResultsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RESULTS_LIST, userLogic.results());
        router.setPage(ConfigurationManager.getProperty("path.page.results"));
        return router;
    }
}
