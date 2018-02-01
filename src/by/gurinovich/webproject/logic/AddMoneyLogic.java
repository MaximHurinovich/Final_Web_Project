package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.CardDAO;

import java.sql.SQLException;

public class AddMoneyLogic {
    private static boolean checkMoney(double amount, double cardAmount){
        return amount <= cardAmount;
    }

    public static boolean addMoney(double amount, double currentAmount, double cardAmount, String cardNumber){
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, cardAmount)&& dao.updateCardToAccount(cardNumber, cardAmount, amount, currentAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static double getAccountAmount(String cardNumber){
        CardDAO dao = new CardDAO();
        double amount = 0;
        try {
            amount= dao.getAccountAmount(cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public static double getCardAmount(String cardNumber){
        CardDAO dao = new CardDAO();
        double amount = 0;
        try {
            amount = dao.getCardAmount(cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
}
