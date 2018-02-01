package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements ActionCommand {
    private static final String PARAM_USER = "userfull";
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        User user = ((User)request.getSession().getAttribute(PARAM_USER));
        request.getSession().setAttribute("cardAmount",
                userLogic.getCardAmount(user.getCardNumber()));
        router.setPage(ConfigurationManager.getProperty("path.page.addmoney"));
        return router;
    }
}
