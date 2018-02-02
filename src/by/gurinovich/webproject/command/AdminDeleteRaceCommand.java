package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminDeleteRaceCommand implements ActionCommand {
    private static final String PARAM_RACE_ID = "race_id";
    private static final String ATTRIBUTE_RACES_LIST = "racesList";
    private AdminLogic adminLogic = new AdminLogic();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        int id = Integer.valueOf(request.getParameter(PARAM_RACE_ID));
        boolean result = adminLogic.deleteRace(id);
        if(result){
            ArrayList<Race> races = (ArrayList<Race>)request.getSession().getAttribute(ATTRIBUTE_RACES_LIST);
            for(Race race: races){
                if(race.getId()==id){
                    races.remove(race);
                }
            }
            request.getSession().setAttribute(ATTRIBUTE_RACES_LIST, races);
            router.setRoute(Router.RouteType.REDIRECT);
        }else {
            request.setAttribute("adminDeleteMessage", MessageManager.getProperty("message.unknownerror"));
        }
        router.setPage(ConfigurationManager.getProperty("path.page.admin.main"));
        return router;
    }
}
