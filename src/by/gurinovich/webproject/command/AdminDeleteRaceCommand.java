package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminDeleteRaceCommand implements ActionCommand {
    private AdminLogic adminLogic = new AdminLogic();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int id = Integer.valueOf(request.getParameter(Constant.ATTRIBUTE_NAME_RACE_ID));
        boolean result;
        try {
            result = adminLogic.deleteRace(id);
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        if (result) {
            ArrayList<Race> races = (ArrayList<Race>) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST);
            for (Race race : races) {
                if (race.getId() == id) {
                    races.remove(race);
                }
            }
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST, races);
            router.setRoute(Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.unknownerror"));
        }
        router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
        return router;
    }
}
