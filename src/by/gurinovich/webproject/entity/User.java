package by.gurinovich.webproject.entity;

public class User {
    private String username;
    private String firstName;
    private String secondName;
    private String cardNumber;
    private String email;
    private double amount;

    public User(String username, String firstName, String secondName, String email){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getAmount() {
        return amount;
    }
}
