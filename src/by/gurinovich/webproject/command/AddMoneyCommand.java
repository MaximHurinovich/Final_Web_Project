package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        User user = ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER));
        try {
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_CARD_AMOUNT,
                    userLogic.getCardAmount(user.getCardNumber()));
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());

        }
        router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
        return router;
    }
}
