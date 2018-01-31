package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.RegistrationDAO;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

public class SignUpLogic {
    public static boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        RegistrationDAO dao = new RegistrationDAO();
        if(dao.checkCard(cardNumber, cardPassword) && dao.checkUserName(userName)) {
            return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
        }
        return false;
    }

    public static String invalidateMessage(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        Validator validator = new Validator();
        if(!validator.checkString(firstName, Validator.NAME_REGEX)||!validator.checkString(secondName, Validator.NAME_REGEX)){
            return MessageManager.getProperty("message.nameerror");
        }
        else if(!validator.checkString(userName, Validator.USERNAME_REGEX)){
            return MessageManager.getProperty("message.usernameerror");
        }
        else if(!validator.checkString(password, Validator.PASSWORD_REGEX)){
            return MessageManager.getProperty("message.passworderror");
        }
        else if(!validator.checkString(email, Validator.EMAIL_REGEX)){
            return MessageManager.getProperty("message.emailerror");
        }
        else if(!validator.checkString(cardNumber, Validator.CARD_REGEX)|| !validator.checkString(cardPassword, Validator.CARD_REGEX)){
            return MessageManager.getProperty("message.carderror");
        }
        else return MessageManager.getProperty("message.unknownerror");
    }
}
