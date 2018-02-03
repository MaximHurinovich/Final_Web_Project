package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class ReturnMoneyCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic logic = new UserLogic();
        if (request.getParameter(Constant.PARAM_NAME_RETURN_MONEY).isEmpty()) {
            request.setAttribute(Constant.ATTRIBUTE_MESSAGE_ADD_MONEY, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
            return router;
        }
        double sum = Double.valueOf(request.getParameter(Constant.PARAM_NAME_RETURN_MONEY));
        User user = (User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER);
        if (logic.returnMoney(sum, user.getAmount(),
                (Double) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_CARD_AMOUNT),
                user.getCardNumber())) {
            user.setAmount(logic.getAccountAmount(user.getCardNumber()));
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_USER, user);
            router.setPage(ConfigurationManager.getProperty("path.page.account"));
            router.setRoute(Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(Constant.ATTRIBUTE_MESSAGE_ADD_MONEY, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
        }
        return router;
    }
}
