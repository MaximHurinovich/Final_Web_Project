package by.gurinovich.webproject.command;


import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.DefaultLogic;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    private static final String PARAM_NAME_USERNAME = "username";
    private static final String PARAM_NAME_FIRSTNAME = "first_name";
    private static final String PARAM_NAME_SECONDNAME = "second_name";
    private static final String PARAM_NAME_EMAIL = "e-mail";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CARD_NUMBER = "card_number";
    private static final String PARAM_NAME_CARD_PASSWORD = "card_password";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router router = new Router();
        DefaultLogic logic = new DefaultLogic();
        String page;
        String userName = request.getParameter(PARAM_NAME_USERNAME);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String firstName = request.getParameter(PARAM_NAME_FIRSTNAME);
        String secondName = request.getParameter(PARAM_NAME_SECONDNAME);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String cardNumber = request.getParameter(PARAM_NAME_CARD_NUMBER);
        String cardPassword = request.getParameter(PARAM_NAME_CARD_PASSWORD);
        try {
            if (logic.checkRegistration(firstName, secondName, userName, password, email, cardNumber, cardPassword)) {
                request.setAttribute(Constant.ATTRIBUTE_SUCCESS_MESSAGE, MessageManager.getProperty("message.registration_success"));
                page = ConfigurationManager.getProperty("path.page.login");
                router.setPage(page);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN_MESSAGE, logic.invalidateMessage(firstName, secondName, userName, password, email, cardNumber, cardPassword));
                page = ConfigurationManager.getProperty("path.page.register");
                router.setPage(page);
            }
            return router;
        }
        catch(LogicalException e){
                throw new CommandException(e.getMessage(), e);
            }
    }
}
