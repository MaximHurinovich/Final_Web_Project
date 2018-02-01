package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.ResultsLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class ResultsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().setAttribute("resultList", ResultsLogic.results());
        router.setPage(ConfigurationManager.getProperty("path.page.results"));
        return router;
    }
}
