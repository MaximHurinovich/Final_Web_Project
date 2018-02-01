package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public class AcceptEditCommand implements ActionCommand {
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_SECONDNAME = "secondname";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_CARD_NUMBER = "cardnumber";
    private static final String PARAM_USER = "userfull";
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String page;
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        String secondName = request.getParameter(PARAM_SECONDNAME);
        String email = request.getParameter(PARAM_EMAIL);
        String cardNumber =request.getParameter(PARAM_CARD_NUMBER);
        if(userLogic.checkData(firstName,secondName, email, cardNumber)){
            if(userLogic.updateProfile(((User)request.getSession().getAttribute(PARAM_USER)).getUsername(),
                    firstName, secondName, email, cardNumber))
            {
                ((User) request.getSession().getAttribute(PARAM_USER)).setFirstName(firstName);
                ((User) request.getSession().getAttribute(PARAM_USER)).setSecondName(secondName);
                ((User) request.getSession().getAttribute(PARAM_USER)).setEmail(email);
                ((User) request.getSession().getAttribute(PARAM_USER)).setCardNumber(cardNumber);
                page = ConfigurationManager.getProperty("path.page.account");
                router.setPage(page);
            }
            else{
                request.setAttribute("editMessage", MessageManager.getProperty("message.wrongaction"));
                page = ConfigurationManager.getProperty("path.page.edit");
                router.setPage(page);
            }
        }
        else{
            request.setAttribute("editMessage", userLogic.invalidateMessage(firstName, secondName, email, cardNumber));
            page = ConfigurationManager.getProperty("path.page.edit");
            router.setPage(page);
        }
        return router;
    }
}
