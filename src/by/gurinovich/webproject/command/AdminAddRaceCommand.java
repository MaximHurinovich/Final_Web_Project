package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class AdminAddRaceCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AdminAddRaceCommand.class);
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        String card = request.getParameter(Constant.PARAM_NAME_CARD);
        String date = request.getParameter(Constant.PARAM_NAME_DATE);
        HashSet<String> horses;
        horses = (HashSet<String>) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_HORSES);
        if (card == null || date == null || horses.size() < 4) {
            request.setAttribute(Constant.ATTRIBUTE_ADD_RACE_MESSAGE, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
            return router;
        } else try {
            if (logic.addNewRace(card, date, horses)) {
                request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.raceadd"));
                LOGGER.info("admin added new race: " + card + ", " + date);
                router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
                router.setRoute(Router.RouteType.REDIRECT);
                return router;
            } else {
                request.setAttribute(Constant.ATTRIBUTE_ADD_RACE_MESSAGE, MessageManager.getProperty("message.infoerror"));
                router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
                return router;
            }
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());
        }

    }
}
