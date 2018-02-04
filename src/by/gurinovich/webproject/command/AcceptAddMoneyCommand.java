package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class AcceptAddMoneyCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        if (request.getParameter(Constant.PARAM_NAME_MONEY).isEmpty()) {
            request.setAttribute(Constant.ATTRIBUTE_MESSAGE_ADD_MONEY, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
            return router;
        }
        double sum = Double.valueOf(request.getParameter(Constant.PARAM_NAME_MONEY));
        User user = (User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER);
        try {
            if (userLogic.addMoney(sum, user.getAmount(),
                    (Double) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_CARD_AMOUNT),
                    user.getCardNumber())) {
                user.setAmount(userLogic.getAccountAmount(user.getCardNumber()));
                request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_USER, user);
                router.setPage(ConfigurationManager.getProperty("path.page.account"));
                router.setRoute(Router.RouteType.REDIRECT);
                return router;
            } else {
                request.setAttribute(Constant.ATTRIBUTE_MESSAGE_ADD_MONEY, MessageManager.getProperty("message.infoerror"));
                router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
                return router;
            }
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
