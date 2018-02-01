package by.gurinovich.webproject.entity;

public class Odd {
    private String date;
    private String horseName;
    private String type;
    private double odd;
    private boolean isActive;
    private boolean isSuccess;

    public Odd(String date, String horseName, String type, double odd, boolean isActive) {
        this.date = date;
        this.horseName = horseName;
        this.type = type;
        this.odd = odd;
        this.isActive = isActive;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}