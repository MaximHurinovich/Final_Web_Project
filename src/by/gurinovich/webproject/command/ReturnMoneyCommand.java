package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.AddMoneyLogic;
import by.gurinovich.webproject.logic.ReturnMoneyLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class ReturnMoneyCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
            double sum = Double.valueOf(request.getParameter("retmoney"));
            User user = (User) request.getSession().getAttribute("userfull");
            if(ReturnMoneyLogic.addMoney(sum, user.getAmount(),
                    (Double)request.getSession().getAttribute("cardAmount"),
                    user.getCardNumber())){
                user.setAmount(AddMoneyLogic.getAccountAmount(user.getCardNumber()));
                request.getSession().setAttribute("userfull", user);
                router.setPage(ConfigurationManager.getProperty("path.page.account"));
                router.setRoute(Router.RouteType.REDIRECT);
                return router;
            }else{
                MessageManager.getProperty("message.unknowncommand");
                router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
                return router;
            }
    }
}
