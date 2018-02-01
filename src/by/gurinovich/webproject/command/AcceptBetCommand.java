package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AcceptBetCommand implements ActionCommand {
    private static final String PARAM_HORSE_NAME = "name";
    private static final String PARAM_BET_TYPE = "type";
    private static final String ATTRIBUTE_RACE_ID = "race_id";
    private static final String PARAM_USER = "userfull";
    private static final String PARAM_AMOUNT = "amount";
    private static final String ATTRIBUTE_BET_MESSAGE = "betMessage";
    private static final String PARAM_ERROR_BET = "errorBet";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String horseName = request.getParameter(PARAM_HORSE_NAME);
        String bet = request.getParameter(PARAM_BET_TYPE);
        Double amount = Double.valueOf(request.getParameter(PARAM_AMOUNT));
        Integer raceId = (Integer)request.getSession().getAttribute(ATTRIBUTE_RACE_ID);
        ArrayList<Horse> horses = userLogic.getHorses(raceId);
        int horse_id = 0;
        for(Horse horse: horses){
            if(horse.getName().equals(horseName)){
                horse_id = horse.getHorseId();
            }
        }
        User user = (User)request.getSession().getAttribute(PARAM_USER);
        if(userLogic.addNewBet(user.getUsername(), raceId, horse_id, bet, amount)&& userLogic.updateAccountBet(user.getUsername(), user.getAmount(),amount)){
            request.setAttribute(ATTRIBUTE_BET_MESSAGE, MessageManager.getProperty("message.betsuccess"));
            router.setPage(ConfigurationManager.getProperty("path.page.main"));
            router.setRoute(Router.RouteType.REDIRECT);
            return router;
        }else{
            request.setAttribute(PARAM_ERROR_BET, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.bet"));
            return router;
        }
    }
}
