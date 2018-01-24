package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.RegistrationDAO;

public class SignUpLogic {
    public static boolean checkRegistration(String firstName, String secondName, String userName, String password, String email, String cardNumber, String cardPassword){
        RegistrationDAO dao = new RegistrationDAO();
        return dao.registerUser(firstName, secondName, userName, password, email, cardNumber, cardPassword);
    }
}
