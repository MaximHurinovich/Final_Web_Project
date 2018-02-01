package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.CardDAO;

import java.sql.SQLException;

public class ReturnMoneyLogic {
    private static boolean checkMoney(double amount, double AccountAmount){
        return amount <= AccountAmount;
    }

    public static boolean addMoney(double amount, double currentAmount, double cardAmount, String cardNumber){
        CardDAO dao = new CardDAO();
        try {
            return checkMoney(amount, currentAmount)&& dao.updateAccountToCard(cardNumber, cardAmount, amount, currentAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
