package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.RegistrationDAO;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.RegisterValidator;

public class SignUpLogic {
    public static boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        RegistrationDAO dao = new RegistrationDAO();
        if(dao.checkCard(cardNumber, cardPassword) && dao.checkUserName(userName)) {
            return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
        }
        return false;
    }

    public static String invalidateMessage(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        RegisterValidator validator = new RegisterValidator();
        if(!validator.checkString(firstName, RegisterValidator.NAME_REGEX)||!validator.checkString(secondName, RegisterValidator.NAME_REGEX)){
            return MessageManager.getProperty("message.nameerror");
        }
        else if(!validator.checkString(userName, RegisterValidator.USERNAME_REGEX)){
            return MessageManager.getProperty("message.usernameerror");
        }
        else if(!validator.checkString(password, RegisterValidator.PASSWORD_REGEX)){
            return MessageManager.getProperty("message.passworderror");
        }
        else if(!validator.checkString(email, RegisterValidator.EMAIL_REGEX)){
            return MessageManager.getProperty("message.emailerror");
        }
        else if(!validator.checkString(cardNumber, RegisterValidator.CARD_REGEX)|| !validator.checkString(cardPassword, RegisterValidator.CARD_REGEX)){
            return MessageManager.getProperty("message.carderror");
        }
        else return "";
    }
}
