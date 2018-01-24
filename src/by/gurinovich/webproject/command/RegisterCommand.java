package by.gurinovich.webproject.command;


import by.gurinovich.webproject.logic.SignUpLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;

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
    public String execute(HttpServletRequest request) {
        String page = null;
        String userName = request.getParameter(PARAM_NAME_USERNAME);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String firstName = request.getParameter(PARAM_NAME_FIRSTNAME);
        String secondName = request.getParameter(PARAM_NAME_SECONDNAME);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String cardNumber = request.getParameter(PARAM_NAME_CARD_NUMBER);
        String cardPassword = request.getParameter(PARAM_NAME_CARD_PASSWORD);
        if (SignUpLogic.checkRegistration(firstName, secondName, userName, password, email, cardNumber, cardPassword)) {
            request.setAttribute("successMessage", MessageManager.getProperty("message.registration_success"));
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.register");
        }
        return page;
    }
}
