package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class AcceptAddMoneyCommand implements ActionCommand {
    private static final String PARAM_MONEY = "money";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        if(request.getParameter(PARAM_MONEY).isEmpty()){
            request.setAttribute("addMessage", MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
            return router;
        }
        double sum = Double.valueOf(request.getParameter(PARAM_MONEY));
        User user = (User) request.getSession().getAttribute("userfull");
        if(userLogic.addMoney(sum, user.getAmount(),
                (Double)request.getSession().getAttribute("cardAmount"),
                user.getCardNumber())){
            user.setAmount(userLogic.getAccountAmount(user.getCardNumber()));
            request.getSession().setAttribute("userfull", user);
            router.setPage(ConfigurationManager.getProperty("path.page.account"));
            router.setRoute(Router.RouteType.REDIRECT);
            return router;
        }else{
            request.setAttribute("addMessage", MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
            return router;
        }
    }
}
