package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.logic.BookmakerLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BookmakerRacesCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        BookmakerLogic logic = new BookmakerLogic();
        ArrayList<Race> bookingRaces = logic.getRaces();
        if(bookingRaces!=null) {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_BOOKMAKER_RACE, bookingRaces);
            router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.races"));
        }else{
            request.setAttribute(Constant.ATTRIBUTE_BOOKMAKER_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.main"));
        }
        return router;
    }
}
