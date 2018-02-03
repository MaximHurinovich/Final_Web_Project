package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AcceptBetCommand implements ActionCommand {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String horseName = request.getParameter(Constant.PARAM_NAME_HORSE);
        String bet = request.getParameter(Constant.PARAM_NAME_BET_TYPE);
        String amountParameter = request.getParameter(Constant.PARAM_NAME_AMOUNT);
        if (amountParameter.isEmpty()) {
            request.setAttribute(Constant.ATTRIBUTE_ERROR_BET, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.bet"));
            return router;
        }
        Double amount = Double.valueOf(amountParameter);

        Integer raceId = (Integer) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_RACE_ID);
        ArrayList<Horse> horses = userLogic.getHorses(raceId);
        int horse_id = 0;
        for (Horse horse : horses) {
            if (horse.getName().equals(horseName)) {
                horse_id = horse.getHorseId();
            }
        }
        User user = (User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER);
        if (userLogic.addNewBet(user.getUsername(), raceId, horse_id, bet, amount) && userLogic.updateAccountBet(user.getUsername(), user.getAmount(), amount)) {
            request.setAttribute(Constant.ATTRIBUTE_BET_MESSAGE, MessageManager.getProperty("message.betsuccess"));
            router.setPage(ConfigurationManager.getProperty("path.page.main"));
            router.setRoute(Router.RouteType.REDIRECT);
            return router;
        } else {
            request.setAttribute(Constant.ATTRIBUTE_ERROR_BET, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.bet"));
            return router;
        }
    }
}
