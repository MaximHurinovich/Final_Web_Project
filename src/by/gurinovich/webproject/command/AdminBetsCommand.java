package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Odd;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminBetsCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        ArrayList<Odd> odds = logic.getOdds();
        request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_BETS_LIST, odds);
        router.setPage(ConfigurationManager.getProperty("path.page.admin.activebets"));
        return router;
    }
}
