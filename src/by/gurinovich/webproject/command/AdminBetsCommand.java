package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminBetsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        ArrayList<Odd> odds;
        try {
            odds = logic.getOdds();
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_BETS_LIST, odds);
        router.setPage(ConfigurationManager.getProperty("path.page.admin.activebets"));
        return router;
    }
}
