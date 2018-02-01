package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.EditDAO;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Validator;

import java.sql.SQLException;

public class EditProfileLogic {
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

    public static boolean updateProfile(String userName, String firstName, String secondName, String email, String cardNumber){
        EditDAO dao = new EditDAO();
        try {
            return dao.updateProfile(userName, firstName, secondName, email, cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
