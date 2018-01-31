package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

public class AcceptEditLogic {
    public static boolean checkData(String firstName, String secondName, String email, String cardNumber){
        Validator validator = new Validator();
        return validator.checkString(firstName, Validator.NAME_REGEX)&& validator.checkString(secondName, Validator.NAME_REGEX) &&
                validator.checkString(email, Validator.EMAIL_REGEX) && validator.checkString(cardNumber, Validator.CARD_REGEX);
    }

    public static String invalidateMessage(String firstName, String secondName, String email, String cardNumber){
        Validator validator = new Validator();
        if(!validator.checkString(firstName, Validator.NAME_REGEX)||!validator.checkString(secondName, Validator.NAME_REGEX)){
            return MessageManager.getProperty("message.nameerror");
        }
        else if(!validator.checkString(email, Validator.EMAIL_REGEX)){
            return MessageManager.getProperty("message.emailerror");
        }
        else if(!validator.checkString(cardNumber, Validator.CARD_REGEX)){
            return MessageManager.getProperty("message.carderror");
        }
        else return MessageManager.getProperty("message.unknownerror");
    }
}
