package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminRunRaceCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AdminRunRaceCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        DefaultLogic defaultLogic = new DefaultLogic();
        Integer raceId = Integer.valueOf(request.getParameter(Constant.ATTRIBUTE_NAME_RACE_ID));
        if (raceId == null) {
            request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            return router;
        }
        try {
            if (logic.runRace(raceId)) {
                request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST, defaultLogic.getRaces());
                request.getSession().setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.racesuccess"));
                LOGGER.info("race #" + raceId + " started");
                router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.wrongaction"));
                router.setPage("path.page.admin.main");
            }
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        return router;
    }
}
