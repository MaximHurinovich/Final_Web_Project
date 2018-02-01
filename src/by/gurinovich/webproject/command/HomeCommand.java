package by.gurinovich.webproject.command;

import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.main"));
        return router;
    }
}
