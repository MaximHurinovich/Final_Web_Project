package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class ReturnMoneyCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic logic = new UserLogic();
            double sum = Double.valueOf(request.getParameter("retmoney"));
            User user = (User) request.getSession().getAttribute("userfull");
            if(logic.returnMoney(sum, user.getAmount(),
                    (Double)request.getSession().getAttribute("cardAmount"),
                    user.getCardNumber())){
                user.setAmount(logic.getAccountAmount(user.getCardNumber()));
                request.getSession().setAttribute("userfull", user);
                router.setPage(ConfigurationManager.getProperty("path.page.account"));
                router.setRoute(Router.RouteType.REDIRECT);
            }else{
                request.setAttribute("addMessage",MessageManager.getProperty("message.unknownerror"));
                router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
            }
            return router;
    }
}
