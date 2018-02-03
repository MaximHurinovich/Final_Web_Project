package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

public class AdminAddRaceCommand implements ActionCommand {
    private final String PARAM_NAME_CARD = "card";
    private final String PARAM_NAME_DATE = "date";
    private final String ATTRIBUTE_ADD_MESSAGE = "addRaceMessage";
    private final String ATTRIBUTE_HORSES = "horsesList";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        String card = request.getParameter(PARAM_NAME_CARD);
        String date = request.getParameter(PARAM_NAME_DATE);
        HashSet<String> horses;
        horses = (HashSet<String>) request.getSession().getAttribute(ATTRIBUTE_HORSES);
        if(card==null||date==null||horses.isEmpty()){
            request.setAttribute(ATTRIBUTE_ADD_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
            return router;
        }else if(logic.addNewRace(card, date, horses)){
            request.setAttribute("adminMessage", MessageManager.getProperty("message.raceadd"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            router.setRoute(Router.RouteType.REDIRECT);
            return router;
        }else{
            request.setAttribute(ATTRIBUTE_ADD_MESSAGE, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
            return router;
        }

    }
}
