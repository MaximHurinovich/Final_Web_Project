package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BookRaceCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router router = new Router();
        int raceId = Integer.valueOf(request.getParameter(Constant.ATTRIBUTE_NAME_RACE_ID));
        ArrayList<Race> races = (ArrayList<Race>) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_BOOKMAKER_RACE);
        Race race = null;
        for(Race temp: races){
            if(raceId==temp.getId()){
                race = temp;
            }
        }
        if(race!=null){
            ArrayList<Horse> horses = race.getHorses();
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_HORSES, horses);
            request.getSession().setAttribute(Constant.ATTRIBUTE_BOOKING_RACE, race);
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_BOOKMAKER_RACE, races);
            router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.bookrace"));
        }else{
            request.setAttribute(Constant.ATTRIBUTE_BOOKMAKER_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.races"));
        }
        return router;
    }
}
