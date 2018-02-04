package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.index"));
        return router;
    }
}
