package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Bookmaker;
import by.gurinovich.webproject.entity.Race;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.BookmakerLogic;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AcceptBookRaceCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AcceptBookRaceCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        BookmakerLogic logic = new BookmakerLogic();
        String[] winBets = request.getParameterValues(Constant.PARAM_NAME_WIN_BETS);
        String[] top3Bets = request.getParameterValues(Constant.PARAM_NAME_TOP3_BETS);
        String[] outsiderBets = request.getParameterValues(Constant.PARAM_NAME_OUTSIDER_BETS);
        Race race = (Race)request.getSession().getAttribute(Constant.ATTRIBUTE_BOOKING_RACE);
        boolean check = true;
        for (String win:
             winBets) {
            if(win == null){
                check = false;
            }
        }
        for (String top3:
                top3Bets) {
            if(top3 == null){
                check = false;
            }
        }
        for (String outsider:
                outsiderBets) {
            if(outsider == null){
                check = false;
            }
        }
        if(race == null){
            check = false;
        }

        int bookmakerID = ((Bookmaker)request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).getBookmakerID();
        if(!check){
            request.setAttribute(Constant.ATTRIBUTE_BOOKMAKER_MESSAGE, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.bookrace"));
        }else try {
            if(logic.setBets(race, winBets, top3Bets, outsiderBets, bookmakerID)){
                request.setAttribute(Constant.ATTRIBUTE_BOOKMAKER_MESSAGE, MessageManager.getProperty("message.betsuccess"));
                request.setAttribute(Constant.ATTRIBUTE_NAME_RACES_LIST, new DefaultLogic().getRaces());
                LOGGER.info("race with id" + race.getId() + " booked.");
                router.setPage(ConfigurationManager.getProperty("path.page.bookmaker.main"));
            }
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        return router;
    }
}
