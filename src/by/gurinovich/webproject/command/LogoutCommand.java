package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router router = new Router();
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        router.setPage(page);
        return router;
    }
}