package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class AdminRunRaceCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        DefaultLogic defaultLogic = new DefaultLogic();
        Integer raceId = Integer.valueOf(request.getParameter(Constant.ATTRIBUTE_NAME_RACE_ID));
        if (raceId == null) {
            request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            return router;
        }
        if (logic.runRace(raceId)) {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST, defaultLogic.getRaces());
            request.getSession().setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.racesuccess"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            router.setRoute(Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage("path.page.admin.main");
        }
        return router;
    }
}
