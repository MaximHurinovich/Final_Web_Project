package by.gurinovich.webproject.command;

import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class AdminRunRaceCommand implements ActionCommand {
    private final String PARAM_NAME_RACE_ID = "race_id";
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        AdminLogic logic = new AdminLogic();
        DefaultLogic defaultLogic = new DefaultLogic();
        Integer raceId = Integer.valueOf(request.getParameter(PARAM_NAME_RACE_ID));
        if(raceId == null){
            request.setAttribute("adminMessage", MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            return router;
        }
        if(logic.runRace(raceId)){
            request.getSession().setAttribute("racesList", defaultLogic.getRaces());
            request.getSession().setAttribute("adminMessage", MessageManager.getProperty("message.racesuccess"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
            router.setRoute(Router.RouteType.REDIRECT);
        }else{
            request.setAttribute("adminMessage", MessageManager.getProperty("message.wrongaction"));
            router.setPage("path.page.admin.main");
        }
        return router;
    }
}
