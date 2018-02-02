package by.gurinovich.webproject.entity;


public class User extends Person {
    private String cardNumber;
    private double amount;


    public User(String username, String password, String firstName, String secondName, String cardNumber, String email, double amount) {
        super(username, password, firstName, secondName, email);
        this.cardNumber = cardNumber;
        this.amount = amount;
        super.setRole("u");
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

}
